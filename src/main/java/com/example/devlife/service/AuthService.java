package com.example.devlife.service;

import com.example.devlife.dto.AuthDto;
import com.example.devlife.security.JwtTokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AuthService {

    // TODO: 로그인 시 예외처리
    private final RedisService redisService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final String SERVER = "Server"; // Redis 서비스 제공자

    /**
     * 로그인
     */
    @Transactional
    public AuthDto.TokenDto login(AuthDto.LoginDto loginDto){

        // 1. ID/PW로 새로운 인증 토큰 생성
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginDto.getProviderId(), loginDto.getPassword());

        // 2. 실제 인증 처리 --> authentication ==> 사용자의 인증 성공 여부 및 인증된 사용자의 정보 포함
        // authenticate() ==> 사용자 인증하고, 인증 결과 반환
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. 토큰 발급하기 --> getName() ==> providerId 가져옴
        return generateToken(SERVER, authentication.getName(), authentication);
    }

    /**
     * 토큰 발급
     */
    @Transactional
    public AuthDto.TokenDto generateToken(String provider, String providerId, Authentication authentication) {
        // Refresh Token이 이미 있을 경우
        if (redisService.getValues("RT(" + provider + "):" + providerId) != null) {
            redisService.deleteValues("RT(" + provider + "):" + providerId); // 삭제
        }

        // Access Token, Refresh Token 생성 및 Redis에 Refresh Token 저장
        AuthDto.TokenDto tokenDto = jwtTokenProvider.createToken(providerId, authentication);
        saveRefreshToken(provider, providerId, tokenDto.getRefreshToken());
        return tokenDto;
    }

    // Refresh Token -> Redis에 저장
    @Transactional
    public void saveRefreshToken(String provider, String providerId, String refreshToken) {
        redisService.setValuesWithTimeout("RT(" + provider + "):" + providerId, // key
                refreshToken, // value
                jwtTokenProvider.getTokenExpirationTime(refreshToken)); // timeout(milliseconds)
    }

    /**
     * 토큰 재발급
     */
    /*@Transactional
    public AuthDto.TokenDto reissue(String requestAccessTokenInHeader, String requestRefreshToken) {

        // 1. Header로부터 accessToken 추출
        String requestAccessToken = resolveToken(requestAccessTokenInHeader);

        // 2. 사용자 정보 가져오기
        Authentication authentication = jwtTokenProvider.getAuthentication(requestAccessToken);
        String principal = authentication.getName();

        // 3. Redis 에서 providerId(사용자 ID)를 기반으로 저장된 Refresh Token 값을 가져옴
        String refreshTokenInRedis = redisService.getValues("RT(" + SERVER + "):" + principal);

        // 만약 Redis에 저장되어 있는 RefreshToken이 없을 경우 (로그아웃했을 때)
        if (ObjectUtils.isEmpty(refreshTokenInRedis)) {
            return null; // -> 재로그인 요청 TODO: 로그인 재시도 --> 예외처리
        }

        // 요청된 RefreshToken의 유효성 검사 & Redis에 저장되어 있는 RT와 같은지 비교
        if(!jwtTokenProvider.validateRefreshToken(requestRefreshToken) || !refreshTokenInRedis.equals(requestRefreshToken)) {
            redisService.deleteValues("RT(" + SERVER + "):" + principal); // 탈취 가능성 -> 삭제
            return null; // -> 재로그인 요청 TODO: 로그인 재시도 --> 예외처리
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 토큰 재발급 및 Redis 업데이트
        redisService.deleteValues("RT(" + SERVER + "):" + principal); // 기존 RT 삭제
        AuthDto.TokenDto tokenDto = jwtTokenProvider.createToken(principal, authentication);
        saveRefreshToken(SERVER, principal, tokenDto.getRefreshToken());
        return tokenDto;
    }*/

    /*// "Bearer {AccessToken}"에서 AccessToken 추출
    public String resolveToken(String requestAccessTokenInHeader) {
        if (requestAccessTokenInHeader != null && requestAccessTokenInHeader.startsWith("Bearer ")) {
            return requestAccessTokenInHeader.substring(7);
        }
        return null;
    }*/

    // 쿠키에서 AccessToken 추출
    public String resolveToken(HttpServletRequest httpServletRequest) {
        String accessToken = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("access-token".equals(cookie.getName())) {
                    accessToken = cookie.getValue();
                    break;
                }
            }
        }

        if (accessToken != null) {
            return accessToken;
        }
        return null;
    }

    // AT가 만료일자만 초과한 유효한 토큰인지 검사
    /*public boolean validate(String requestAccessTokenInHeader) {
        String requestAccessToken = resolveToken(requestAccessTokenInHeader);
        return jwtTokenProvider.validateAccessTokenOnlyExpired(requestAccessToken); // true = 재발급
    }*/

    /**
     * 로그아웃
     */
    public void logOut(HttpServletRequest request) {

        // 1. Access Token 가져옴
        String requestAccessToken = resolveToken(request);

        // 2. Access Token에 담겨 있는 사용자 정보 가져옴
        String principal = jwtTokenProvider.getAuthentication(requestAccessToken).getName();

        // 3. 해당 사용자의 refresh token이 redis에 있는지 확인 --> 있다면 Refresh Token 삭제
        String refreshTokenInRedis = redisService.getValues("RT(" + SERVER + "):" + principal);
        if (refreshTokenInRedis != null) {
            redisService.deleteValues("RT(" + SERVER + "):" + principal);
        }

        // 4. Redis에 로그아웃 처리한 AccessToken 저장 --> blackList로 저장
        Long expiration = jwtTokenProvider.getTokenExpirationTime(requestAccessToken) - new Date().getTime();
        redisService.setValuesWithTimeout(requestAccessToken, "logout", expiration);


    }
}
