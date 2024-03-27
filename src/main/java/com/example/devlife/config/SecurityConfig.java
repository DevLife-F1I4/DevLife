package com.example.devlife.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public WebSecurityCustomizer configure() {      // 스프링 시큐리티 기능 비활성화
        return web -> web.ignoring().requestMatchers();
    }

    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

                // HTTP Basic 인증 X -> JWT 인증
                .httpBasic(HttpBasicConfigurer::disable)

                // 세션 생성 X 요청마다 사용자 인증 상태 확인하여 처리 -> JWT
                .sessionManagement(configurer ->
                        configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 인증, 인가 설정
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/login", "/signup", "/main",
                                        "/swagger-ui/**", "/api-docs/swagger-config").permitAll() // 인증 없이 접근 허용
                                .requestMatchers("/admin/**").hasRole("ADMIN") // ADMIN만 접근 가능
                                .anyRequest().authenticated())

                // 로그인
                .formLogin(auth -> auth.loginPage("/login")     // 폼 기반 로그인 설정
                        .defaultSuccessUrl("/main"))            // 로그인 성공 시 redirect url

                // 로그아웃
                .logout(auth -> auth.logoutSuccessUrl("/login") // 로그아웃 후 redirect url
                        .invalidateHttpSession(true))           // 세션 무효화

                // exception 처리
                .exceptionHandling(authenticationManager -> authenticationManager
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 인증이 실패했을 때 호출 -> 인증되지 않은 사용자가 보호된 리소스에 접근
                        .accessDeniedHandler(jwtAccessDeniedHandler)) // 접근 거부가 발생했을 때 호출 -> 인증된 사용자가 해당 리소스에 권한 X

                // JWT filter 적용
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)

                .csrf(auth -> auth.disable()); // csrf 비활성화

        return httpSecurity.build();
    }

    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
