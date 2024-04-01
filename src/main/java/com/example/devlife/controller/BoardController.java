package com.example.devlife.controller;

import com.example.devlife.dto.NewBoardRequestDto;
import com.example.devlife.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/api/board")
    public String writeForm() {
        return "board/new";
    }

    @PostMapping("/api/board")
    public String postBoard(NewBoardRequestDto newBoardRequestDto, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boardService.saveBoard(newBoardRequestDto, userDetails.getUsername());

        return "redirect:/";
    }

}
