package com.example.devlife.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.devlife.dto.UpdateCommentRequest;
import com.example.devlife.dto.UpdateUserGradeRequest;
import com.example.devlife.entity.User;
import com.example.devlife.service.admin.AdminService;
import com.example.devlife.service.comment.CommentService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/api/admin")
@Controller
public class AdminController {
	private final AdminService adminService;
	private final CommentService commentService;


	@DeleteMapping("/user/{providerId}")
	public void deleteUser(@PathVariable(value = "providerId") String providerId) {
		//TODO 1. 관리자인지 확인
		adminService.delete(providerId);
	}

	@GetMapping("/user/{providerId}")
	public ResponseEntity<User> getUserInfo(@PathVariable(value = "providerId") String providerId){
		//TODO 1. 관리자 권한 여부 확인
		User user = adminService.getUser(providerId);
		return ResponseEntity.ok().body(user);
	}

	@PatchMapping("/user/{providerId}")
	public ResponseEntity<User> updateUserGrade(@PathVariable(value = "providerId") String providerId, @RequestBody
	UpdateUserGradeRequest request){
		//TODO 1. 관리자 권한 여부 확인
		User user = adminService.updateUserGrade(providerId, request);
		return ResponseEntity.ok().body(user);
	}

	@DeleteMapping("/comment/{commentId}")
	public void deleteComment(@PathVariable(value = "commentId") Long commentId){
		//TODO 1. 관리자 권한 여부 확인
		commentService.deleteComment(commentId);
	}
}