package com.example.devlife.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.devlife.dto.AddCommentRequest;
import com.example.devlife.dto.UpdateCommentRequest;
import com.example.devlife.entity.Board;
import com.example.devlife.entity.Comment;
import com.example.devlife.entity.User;
import com.example.devlife.service.comment.CommentService;

@Controller
public class CommentController {
	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("/api/comment/{boardId}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Comment> addComment(
			@AuthenticationPrincipal User user,
			@PathVariable(value = "boardId") Long boardId,
			@RequestBody AddCommentRequest request) {
		Comment savedComment = commentService.save(user, boardId, request);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
	}

	@GetMapping("/api/comment/{boardId}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Comment>> readComment(
			@PathVariable(value = "boardId") Long boardId) {
		List<Comment> comments = commentService.getComments(boardId);
		return ResponseEntity.ok().body(comments);
	}

	@PatchMapping("/api/comment/{commentId}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Comment> updateComment(
			@AuthenticationPrincipal User user,
			@PathVariable(value = "commentId") Long commentId,
			@RequestBody UpdateCommentRequest request) {
		Comment originalComment = commentService.getComment(commentId);
		if (originalComment.getUser().getId() != user.getId()) {
			return ResponseEntity.badRequest().body(null);
		}
		Comment updatedComment = commentService.updateComment(commentId, request);
		return ResponseEntity.ok().body(updatedComment);
	}

	@DeleteMapping("/api/comment/{commentId}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Void> deleteComment(
			@AuthenticationPrincipal User user,
			@PathVariable(value = "commentId") Long commentId) {
		Comment originalComment = commentService.getComment(commentId);
		if (originalComment.getUser().getId() != user.getId()) {
			return ResponseEntity.badRequest().body(null);
		}
		commentService.deleteComment(commentId);
		return ResponseEntity.ok().body(null);
	}
}
