package com.example.devlife.controller;

import com.example.devlife.dto.AuthDto;
import com.example.devlife.dto.UserAccount;
import com.example.devlife.entity.User;
import com.example.devlife.service.AuthService;
import com.example.devlife.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserViewController {

    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account")
                           User account, Model model){

        if(account != null) {
            log.info("메인페이지 " + account.getProviderId());
            model.addAttribute(account);
        }
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
