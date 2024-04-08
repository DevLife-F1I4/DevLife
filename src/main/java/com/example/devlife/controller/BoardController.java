package com.example.devlife.controller;

import com.example.devlife.dto.BoardResponseDto;
import com.example.devlife.dto.BoardWriteRequestDto;
import com.example.devlife.entity.*;
import com.example.devlife.repository.user.UserRepository;
import com.example.devlife.service.board.BoardService;
import com.example.devlife.service.comment.CommentService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final UserRepository userRepository;
    private final CommentService commentService;

    //글작성 GET
    @GetMapping("/write")
    public String boardWriteForm(@AuthenticationPrincipal
                                 (expression = "#this == 'anonymousUser' ? null : account")
                                 User account, Model model) {
        if(account != null) {
            model.addAttribute("account", account);
        }
        model.addAttribute("category", Category.values());
        model.addAttribute("grade", Grade.values());
        return "board/write";
    }

    //글작성 POST
    @PostMapping("/write")
    public String postBoardWrite(@AuthenticationPrincipal
                                 (expression = "#this == 'anonymousUser' ? null : account")
                                 User account,
                                 @RequestBody BoardWriteRequestDto boardWriteRequestDto) {
        boardService.saveBoard(boardWriteRequestDto, account.getProviderId());
        return "redirect:/board/list";
    }

    //글상세 GET
    @GetMapping("/{id}")
    public String boardDetail(
    		@AuthenticationPrincipal (expression = "#this == 'anonymousUser' ? null : account") User user,
    		@PathVariable Long id, Model model) {

        BoardResponseDto result = boardService.boardDetail(id);

        model.addAttribute("dto", result);
        model.addAttribute("board_id", id);
        
        List<Comment> comments = commentService.getComments(id);
		comments.sort((c1, c2)->Long.compare(c1.getId(), c2.getId()));
		model.addAttribute("comments", comments);
		if (user != null) {
			model.addAttribute("account", user);
		}

        if(user.getGrade().ordinal() < result.getGrade().ordinal() && (!Objects.equals(user.getProviderId(), result.getUser().getProviderId()))  && (user.getRole() == Role.USER)  ) {
            return "board/YouShallNotPass";
        }
        else{
            return "board/detail";
        }
    }

    //글수정 GET
    @GetMapping("/{id}/update")
    public String boardUpdateForm(@PathVariable Long id, Model model,
                                  @AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account") User account) {
        BoardResponseDto result = boardService.boardDetail(id);
        if (!Objects.equals(result.getUser().getProviderId(), account.getProviderId()) && (account.getRole() == Role.USER) ) {
            return "board/YouShallNotPass";
        }

        if(account != null) {
            model.addAttribute("account", account);
        }
        model.addAttribute("dto", result);
        model.addAttribute("board_id", id);
        model.addAttribute("category", Category.values());
        model.addAttribute("grade", Grade.values());

        return "board/update";
    }

    //글수정 POST
    @PostMapping("/{id}/update")
    public String postBoardUpdate(@PathVariable Long id, @RequestBody BoardWriteRequestDto boardWriteRequestDTO) {
        boardService.boardUpdate(id, boardWriteRequestDTO);

        return "redirect:/board/" + id;
    }

    //글삭제
    @DeleteMapping("/{id}")
    public String boardRemove(@PathVariable Long id, Model model,
                              @AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account") User account) {
//        if(account != null) {
//            model.addAttribute("account", account);
//        }

        BoardResponseDto result = boardService.boardDetail(id);

        if (Objects.equals(result.getUser().getProviderId(), account.getProviderId())) {
            boardService.deleteBoard(id);
            return "redirect:/board/list";
        }
        else if(Objects.equals(account.getRole(), Role.ADMIN)){
            boardService.deleteBoard(id);
            return "redirect:/board/list";
        }
        else{
            return "board/YouShallNotPass";
        }
    }
}
