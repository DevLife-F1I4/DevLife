package com.example.devlife.service.admin;

import org.springframework.stereotype.Service;

import com.example.devlife.dto.UpdateUserGradeRequest;
import com.example.devlife.entity.Comment;
import com.example.devlife.entity.User;
import com.example.devlife.repository.board.BoardRepository;
import com.example.devlife.repository.comment.CommentRepository;
import com.example.devlife.repository.user.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AdminService {

	private final UserRepository userRepository;
	private final BoardRepository boardRepository;
	private final CommentRepository commentRepository;

	@Transactional
	public void deleteUser(String providerId) {
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

	public void deleteComment(Long commentId){
		commentRepository.deleteById(commentId);
	}

	public void deleteBoard(Long boardId){
		boardRepository.deleteById(boardId);
	}
}
