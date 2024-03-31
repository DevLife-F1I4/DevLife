package com.example.devlife.controller;

import com.example.devlife.dto.UserAccount;
import com.example.devlife.dto.UserDto;
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

    @PutMapping("/user/me")
    public ResponseEntity<?> updateMyInfo(@AuthenticationPrincipal UserAccount userAccount,
                                          @Valid @RequestBody UserDto.UserRequest request) {
        log.info("로그인 유저 아이디 " + userAccount.getUsername());
        userService.updateUserInfo(userAccount.getUsername(), request );
        return ResponseEntity.noContent().build();
    }

    /*@GetMapping("/user/boards")
    public ResponseEntity<?> getMyBoards() {


    }

    @GetMapping("/user/comments")
    public ResponseEntity<?> getMyComments() {

    }*/

}
