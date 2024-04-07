package com.example.devlife.controller;

import com.example.devlife.entity.Board;
import com.example.devlife.entity.Category;
import com.example.devlife.entity.User;
import com.example.devlife.service.board.BoardService;
import com.example.devlife.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardListController {
    private final BoardService boardService;
    private final CommentService commentService;

    //전체 글목록 조회
    @GetMapping("/list")
    public String findAll(Model model,
                          @AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account") User account) {
        //DB에서 전체 게시글 데이터 가져온 후 list.html에 표시
        List<Board> boardList = boardService.findAll();
        model.addAttribute("boardList", boardList);
        model.addAttribute("account", account);
        return "/board/list";
    }

    @GetMapping("/list/{category}")
    public String findAllByACategory(Model model, @PathVariable Category category,
                          @AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account") User account) {
        //DB에서 전체 게시글 데이터 가져온 후 list.html에 표시
        List<Board> boardList = boardService.findAllByCategory(category);
        model.addAttribute("boardList", boardList);
        model.addAttribute("account", account);
        return "/board/list";
    }
}
