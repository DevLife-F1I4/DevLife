package com.example.devlife.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.devlife.dto.UpdateUserGradeRequest;
import com.example.devlife.entity.Role;
import com.example.devlife.entity.User;
import com.example.devlife.service.admin.AdminService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/api/admin")
@Controller
public class AdminController {
	private final AdminService adminService;


	@DeleteMapping("/user/{providerId}/{flag}")
	public ResponseEntity<Void> deleteUser(@PathVariable(value = "providerId") String providerId,
											@PathVariable(value="flag") Boolean flag) {
		
		adminService.deleteUser(providerId, flag);
		return ResponseEntity.ok().body(null);
	}

	@GetMapping("/user/{providerId}")
	public ResponseEntity<User> getUserInfo(@PathVariable(value = "providerId") String providerId){
		User user = adminService.getUser(providerId);
		return ResponseEntity.ok().body(user);
	}

	@PatchMapping("/user/{providerId}")
	public ResponseEntity<User> updateUserGrade(@PathVariable(value = "providerId") String providerId,
												@RequestBody UpdateUserGradeRequest request){
		User user = adminService.updateUserGrade(providerId, request);
		return ResponseEntity.ok().body(user);
	}

	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<Void> deleteComment(
			@PathVariable(value = "commentId") Long commentId){
		//TODO 1. 관리자 권한 여부 확인
		adminService.deleteComment(commentId);
		return ResponseEntity.ok().body(null);
	}

	@DeleteMapping("/board/{boardId}")
	public ResponseEntity<Void> deleteBoard(@PathVariable(value = "boardId") Long boardId){
		//TODO 1. 관리자 권한 여부 확인
		adminService.deleteBoard(boardId);
		return ResponseEntity.ok().body(null);
	}
}
