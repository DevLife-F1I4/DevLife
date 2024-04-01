package com.example.devlife.service.admin;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.devlife.dto.UpdateCommentRequest;
import com.example.devlife.dto.UpdateUserGradeRequest;
import com.example.devlife.entity.Grade;
import com.example.devlife.entity.User;
import com.example.devlife.repository.user.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminService {

	private final UserRepository userRepository;

	public AdminService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public void delete(String providerId) {
		User user = userRepository.findByProviderId(providerId)
			.orElseThrow(() -> new IllegalArgumentException("not found: " + providerId));
		user.withdrawUser(true);
	}

	public User getUser(String providerId){
		User user = userRepository.findByProviderId(providerId)
			.orElseThrow(() -> new IllegalArgumentException("not found: " + providerId));
		return user;
	}

	@Transactional
	public User updateUserGrade(String providerId, UpdateUserGradeRequest request){
		User user = userRepository.findByProviderId(providerId)
			.orElseThrow(() -> new IllegalArgumentException("not found: " + providerId));
		user.updateGrade(request.getGrade());
		return user;
	}
}
