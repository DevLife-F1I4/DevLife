package com.example.devlife.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 유저 정보는 있으나 자원에 접근할 수 있는 권한이 없는 경우 -> 인증 실패
 * ex) 관리자 페이지에 일반 유저가 접근할 때
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        response.setCharacterEncoding("utf-8");
        response.sendError(403, "권한이 없습니다.");
    }
}
