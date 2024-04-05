package com.example.devlife.controller;

import com.example.devlife.dto.BoardResponseDto;
import com.example.devlife.dto.BoardWriteRequestDto;
import com.example.devlife.entity.Category;
import com.example.devlife.entity.User;
import com.example.devlife.repository.user.UserRepository;
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
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final UserRepository userRepository;

    //글작성 GET
    @GetMapping("/write")
    public String boardWriteForm(Model model,
                                 @AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account") User account) {
        model.addAttribute("category", Category.values());
        model.addAttribute("account", account);
        return "board/write";
    }

    //글작성 POST
    @PostMapping("/write")
    public String postBoardWrite(BoardWriteRequestDto boardWriteRequestDto,
                                 @AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account") User account) {
        boardService.saveBoard(boardWriteRequestDto, account.getProviderId());
        return "redirect:/";
    }

    //글상세 GET
    @GetMapping("/{id}")
    public String boardDetail(@PathVariable Long id, Model model) {
        BoardResponseDto result = boardService.boardDetail(id);

        model.addAttribute("dto", result);
        model.addAttribute("board_id", id);

        return "board/detail";
    }

    //글수정 GET
    @GetMapping("/{id}/update")
    public String boardUpdateForm(@PathVariable Long id, Model model, Category category,
                                  @AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account") User account) {
        BoardResponseDto result = boardService.boardDetail(id);
        if (!Objects.equals(result.getUser().getProviderId(), account.getProviderId())) {
            return "redirect:/";
        }

        model.addAttribute("dto", result);
        model.addAttribute("board_id", id);
        model.addAttribute("category", Category.values());

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
    public String boardRemove(@PathVariable Long id,
                              @AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account") User account) {
        BoardResponseDto result = boardService.boardDetail(id);
        if (!Objects.equals(result.getUser().getProviderId(), account.getProviderId())) {
            return "redirect:/";
        }

        boardService.deleteBoard(id);

        return "redirect:/";
    }
}