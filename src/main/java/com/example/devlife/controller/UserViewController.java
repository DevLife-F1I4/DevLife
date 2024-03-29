package com.example.devlife.controller;

import com.example.devlife.dto.AuthDto;
import com.example.devlife.service.AuthService;
import com.example.devlife.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserViewController {

    @GetMapping("/main")
    public String mainPage(){
        return "main";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/mypage")
    public String mypage() {
        return "mypage";
    }
}
