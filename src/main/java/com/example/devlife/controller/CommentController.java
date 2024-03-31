package com.example.devlife.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Comment> addComment(@PathVariable(value = "boardId") Long boardId,
		@RequestBody AddCommentRequest request) {
		//request - content, parentId, depth, sequence
		//TODO 0. User 로그인 정보 확인
		User user = null;
		Comment savedComment = commentService.save(user, boardId, request);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
	}

	@GetMapping("/api/comment/{boardId}")
	public ResponseEntity<List<Comment>> readComment(@PathVariable(value = "boardId") Long boardId) {
		//TODO 1. User 로그인 정보 확인
		List<Comment> comments = commentService.getComments(boardId);
		return ResponseEntity.ok().body(comments);
	}

	@PatchMapping("/api/comment/{commentId}")
	public ResponseEntity<Comment> updateComment(@PathVariable(value = "commentId") Long commentId, @RequestBody
		UpdateCommentRequest request) {
		//TODO 1. User 로그인 정보 확인
		Comment updatedComment = commentService.updateComment(commentId, request);
		return ResponseEntity.ok().body(updatedComment);
	}

	@DeleteMapping("/api/comment/{commentId}")
	public ResponseEntity<Void> deleteComment(@PathVariable(value = "commentId") Long commentId) {
		//TODO 1. User 로그인 정보 확인
		commentService.deleteComment(commentId);
		return ResponseEntity.ok().body(null);
	}
}