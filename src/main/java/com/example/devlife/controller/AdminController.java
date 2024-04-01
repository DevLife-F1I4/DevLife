package com.example.devlife.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.devlife.entity.User;
import com.example.devlife.service.admin.AdminService;

@Controller
public class AdminController {
	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@DeleteMapping("/api/admin/user/{providerId}")
	public void deleteUser(@PathVariable(value = "providerId") String providerId) {
		//TODO 1. 관리자인지 확인
		adminService.delete(providerId);
	}


}
