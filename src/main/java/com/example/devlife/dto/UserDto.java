package com.example.devlife.dto;

import com.example.devlife.entity.Grade;
import com.example.devlife.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

public class UserDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserResponse{
        private String providerId;
        private String nickname;
        private Grade grade;

        public static UserResponse from(User user){
            return new UserResponse(
                    user.getProviderId(),
                    user.getNickname(),
                    user.getGrade());
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRequest{
        private String nickname;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginUser{
        private Long id;
    }
}