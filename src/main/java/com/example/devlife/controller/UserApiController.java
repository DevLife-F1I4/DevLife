package com.example.devlife.controller;

import com.example.devlife.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    /**
     * 마이페이지 --> 내 정보 조회
     */
    /*@GetMapping("/user/me")
    public ResponseEntity<?> getMyInfo(){

    }

    @GetMapping("/user/boards")
    public ResponseEntity<?> getMyBoards(){

    }

    @GetMapping("/user/comments")
    public ResponseEntity<?> getMyComments(){

    }
*/
}
