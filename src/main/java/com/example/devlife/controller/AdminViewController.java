package com.example.devlife.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.devlife.entity.User;
import com.example.devlife.service.admin.AdminService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/adminpage")
@Controller
public class AdminViewController {
	private final AdminService adminService;
	@GetMapping("")
	public String adminMain(
			@AuthenticationPrincipal (expression = "#this == 'anonymousUser' ? null : account") User account,
			Model model
			) {
		if(account != null) {
            model.addAttribute("account", account);
        }
		return "admin/main";
	}
	
	@GetMapping("/{providerId}")
	public String adminMain(
			@AuthenticationPrincipal (expression = "#this == 'anonymousUser' ? null : account") User account,
			@PathVariable(value = "providerId") String providerId,
			Model model
			) {
		if(account != null) {
            model.addAttribute("account", account);
        }
		User user = adminService.getUser(providerId);
		if (user != null) {
			model.addAttribute("user", user);
		}
		return "admin/main";
	}
}
