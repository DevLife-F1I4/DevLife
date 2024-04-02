package com.example.devlife.controller;


import com.example.devlife.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserViewController {

    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account")
                           User account, Model model){
        if(account != null) {
            log.info("메인페이지 " + account.getNickname());
            model.addAttribute("account", account);
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
    public String mypage(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account")
                             User account, Model model) {
        if(account != null) {
            log.info("마이페이지 " + account.getProviderId());
            model.addAttribute("account", account);
        }
        return "mypage";
    }
}
