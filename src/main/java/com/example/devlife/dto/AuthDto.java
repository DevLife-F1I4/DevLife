package com.example.devlife.dto;

import com.example.devlife.entity.Grade;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuthDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class LoginDto {
        private String providerId;
        private String password;

        @Builder
        public LoginDto(String providerId, String password) {
            this.providerId = providerId;
            this.password = password;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpDto {
        private String providerId;
        private String password;
        private String nickname;
        private Grade grade;

        @Builder
        public SignUpDto(String providerId, String password, String nickname) {
            this.providerId = providerId;
            this.password = password;
            this.nickname=nickname;
            this.grade= Grade.F1;
        }

        public static SignUpDto encodePassword(SignUpDto signUpDto, String encodedPassword) {
            SignUpDto newSignupDto = new SignUpDto();
            newSignupDto.providerId = signUpDto.getProviderId();
            newSignupDto.password = encodedPassword;
            newSignupDto.nickname= signUpDto.getNickname();
            return newSignupDto;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TokenDto {
        private String accessToken;
        private String refreshToken;

        public TokenDto(String accessToken, String refreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }
}
