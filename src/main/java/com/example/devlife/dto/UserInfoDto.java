package com.example.devlife.dto;

import com.example.devlife.entity.Grade;
import com.example.devlife.entity.User;
import lombok.*;

public class UserInfoDto {

    @Getter
    @AllArgsConstructor
    @Setter
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
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserBoardResponse{
        private String providerId;
        private String nickname;
        private Grade grade;

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
    public static class UserIdRequest{
        private String providerId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginUser{
        private Long id;
    }
}