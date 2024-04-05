package com.example.devlife.controller;

import com.example.devlife.dto.UserInfoDto;
import com.example.devlife.entity.User;
import com.example.devlife.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    /**
     * 마이페이지 --> 내 정보 조회
     */
    @GetMapping("/user/me")
    public ResponseEntity<UserInfoDto.UserResponse> getMyInfo(@AuthenticationPrincipal User userAccount) {
        log.info("로그인 유저 아이디 " + userAccount.getProviderId());
        UserInfoDto.UserResponse userInfo = userService.getUserInfo(userAccount.getProviderId());
        return ResponseEntity.ok(userInfo);
    }

    /**
     * 내 정보 수정 (닉네임 수정)
     */
    @PutMapping("/user/me")
    public ResponseEntity<?> updateMyInfo(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account")
                                              User account,
                                          @Valid @RequestBody UserInfoDto.UserRequest request) {
        log.info("로그인 유저 아이디 " + account.getProviderId());
        userService.updateUserInfo(account.getProviderId(), request );
        return ResponseEntity.noContent().build();
    }



    /**
     * 내가 작성한 글 조회
     */
    /*@GetMapping("/user/boards")
    public ResponseEntity<?> getMyBoards(@AuthenticationPrincipal User userAccount) {
        log.info("로그인 유저 아이디 " + userAccount.getProviderId());
    }*/

    /**
     * 내가 작성한 댓글 조회
     */
    /*@GetMapping("/user/comments")
    public ResponseEntity<?> getMyComments() {
    }*/

}
