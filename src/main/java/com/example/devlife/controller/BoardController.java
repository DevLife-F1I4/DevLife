package com.example.devlife.controller;

import com.example.devlife.dto.BoardResponseDto;
import com.example.devlife.dto.BoardWriteRequestDto;
import com.example.devlife.security.UserDetailsImpl;
import com.example.devlife.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;

    //글작성 GET
    @GetMapping
    public String boardWriteForm() {
        return "board/write";
    }

    //글작성 POST
    @PostMapping
    public String postBoardWrite(BoardWriteRequestDto boardWriteRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        boardService.saveBoard(boardWriteRequestDto, userDetailsImpl.getUsername());
        return "redirect:/";
    }

    //글수정 GET
    @GetMapping("/{id}")
    public String boardUpdateForm(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        BoardResponseDto result = boardService.boardDetail(id);
        if (!Objects.equals(result.getUser().getProviderId(), userDetailsImpl.getUsername())) {
            return "redirect:/";
        }

        model.addAttribute("dto", result);
        model.addAttribute("board_id", id);

        return "board/update";
    }

    //글수정 POST
    @PostMapping("/{id}/update")
    public String postBoardUpdate(@PathVariable Long id, BoardWriteRequestDto boardWriteRequestDTO) {
        boardService.boardUpdate(id, boardWriteRequestDTO);

        return "redirect:/board/" + id;
    }

    //글삭제
    @DeleteMapping("/{id}")
    public String boardRemove(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        BoardResponseDto result = boardService.boardDetail(id);
        if (!Objects.equals(result.getUser().getProviderId(), userDetailsImpl.getUsername())) {
            return "redirect:/";
        }

        boardService.deleteBoard(id);

        return "redirect:/";
    }
}
