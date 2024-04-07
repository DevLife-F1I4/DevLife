package com.example.devlife.controller;


import com.example.devlife.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Slf4j
@Controller
@RequiredArgsConstructor
public class UserViewController {

    @GetMapping(value = {"/", "/main"})
    public String mainPage(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account")
                           User account, Model model, @AuthenticationPrincipal Principal principal) {
        if(principal!=null){
            log.info("유저 인증 " + principal.getName());
        }
        if (account != null) {
            log.info("유저 아이디 : " + account.getProviderId());
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
    @GetMapping("/edit-nickname")
    public String showEditNicknameForm(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account")
                                           User account, Model model) {
        // 사용자의 닉네임 정보를 가져와서 모델에 추가
        String nickname = account.getNickname(); // 예시: userService에서 현재 사용자의 닉네임을 가져오는 메서드
        model.addAttribute("nickname", nickname);
        model.addAttribute("account", account);
        return "edit-nickname"; // HTML 파일 이름 리턴
    }

}
