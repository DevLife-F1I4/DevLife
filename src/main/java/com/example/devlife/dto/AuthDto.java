package com.example.devlife.dto;

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
    public static class SignupDto {
        private String providerId;
        private String password;

        @Builder
        public SignupDto(String providerId, String password) {
            this.providerId = providerId;
            this.password = password;
        }

        public static SignupDto encodePassword(SignupDto signupDto, String encodedPassword) {
            SignupDto newSignupDto = new SignupDto();
            newSignupDto.providerId = signupDto.getProviderId();
            newSignupDto.password = encodedPassword;
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
