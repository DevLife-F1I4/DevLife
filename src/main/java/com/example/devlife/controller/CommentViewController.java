package com.example.devlife.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.devlife.entity.User;
import com.example.devlife.service.comment.CommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentViewController {
	private final CommentService commentService;
	
	@GetMapping("/{boardId}")
	@PreAuthorize("isAuthenticated()")
	public String listView(
			@AuthenticationPrincipal User user,
			@PathVariable(value="boardId") Long boardId,
			Model model
			) {
		model.addAttribute("boardId", boardId);
		model.addAttribute("account", user);
		return "comment/list";
	}
}
