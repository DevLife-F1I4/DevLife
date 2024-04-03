package com.example.devlife.controller;

import com.example.devlife.dto.AuthDto;
import com.example.devlife.dto.UserInfoDto;
import com.example.devlife.service.AuthService;
import com.example.devlife.service.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthApiController {

    private final AuthService authService;
    private final UserService userService;
    private final long COOKIE_EXPIRATION = 60*60*24*7; // 7일

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserInfoDto.UserResponse> signUp(@RequestBody @Valid AuthDto.SignUpDto signUpDto) {
        return ResponseEntity.ok(userService.signUp(signUpDto));
    }

    /**
     * 아이디 중복 여부 확인
     */
    @PostMapping("/signup/checkId")
    public ResponseEntity<Boolean> checkId(@RequestBody UserInfoDto.UserIdRequest idRequest) {
        return ResponseEntity.ok(userService.validateDuplicateId(idRequest.getProviderId()));
    }

    /**
     * 닉네임 중복 여부 확인
     */
    @PostMapping("/signup/checkNickname")
    public ResponseEntity<Boolean> checkNickname(@RequestBody UserInfoDto.UserRequest nickname) {
        return ResponseEntity.ok(userService.validateDuplicateNickname(nickname.getNickname()));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthDto.LoginDto loginDto) {
        AuthDto.TokenDto tokenDto = authService.login(loginDto);

        // Refresh Token 저장
        HttpCookie refreshCookie = ResponseCookie.from("refresh-token", tokenDto.getRefreshToken())
                .maxAge(COOKIE_EXPIRATION)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .build();

        // Access Token 저장
        HttpCookie accessCookie = ResponseCookie.from("access-token", tokenDto.getAccessToken())
                .maxAge(COOKIE_EXPIRATION)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .build();

        /*return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, httpCookie.toString())
                // AccessToken 저장
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenDto.getAccessToken())
                .build();*/

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, refreshCookie.toString())
                // AccessToken 저장
                .header(HttpHeaders.SET_COOKIE, accessCookie.toString())
                .build();
    }

    /*@PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestHeader("Authorization") String requestAccessToken) {
        if (!authService.validate(requestAccessToken)) {
            return ResponseEntity.status(HttpStatus.OK).build(); // 재발급 필요X
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 재발급 필요
        }
    }

    // 토큰 재발급
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@CookieValue(name = "refresh-token") String requestRefreshToken,
                                     @RequestHeader("Authorization") String requestAccessToken) {
        AuthDto.TokenDto reissuedTokenDto = authService.reissue(requestAccessToken, requestRefreshToken);

        if (reissuedTokenDto != null) { // 토큰 재발급 성공
            // Refresh Token 저장
            ResponseCookie responseCookie = ResponseCookie.from("refresh-token", reissuedTokenDto.getRefreshToken())
                    .maxAge(COOKIE_EXPIRATION)
                    .httpOnly(true)
                    .secure(true)
                    .build();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                    // Access Token 저장
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + reissuedTokenDto.getAccessToken())
                    .build();

        } else { // Refresh Token 탈취 가능성
            // Cookie 삭제 후 재로그인 유도
            ResponseCookie responseCookie = ResponseCookie.from("refresh-token", "")
                    .maxAge(0)
                    .path("/")
                    .build();
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                    .build();
        }
    }*/

    @PostMapping("/user/logout")
    public ResponseEntity<?> logOut(HttpServletRequest request, HttpServletResponse response) {
        authService.logOut(request);
        expireCookie(response, "access-token");
        ResponseCookie responseCookie = ResponseCookie.from("refresh-token", "")
                .maxAge(0)
                .path("/")
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .build();
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
