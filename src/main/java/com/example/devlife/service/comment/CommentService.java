package com.example.devlife.service.comment;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.devlife.dto.AddCommentRequest;
import com.example.devlife.dto.UpdateCommentRequest;
import com.example.devlife.entity.Board;
import com.example.devlife.entity.Comment;
import com.example.devlife.entity.User;
import com.example.devlife.repository.board.BoardRepository;
import com.example.devlife.repository.comment.CommentRepository;
import com.example.devlife.repository.user.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;
	private final UserRepository userRepository;
	
	public Comment save(User user, Long boardId, AddCommentRequest request) {
		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException("not found: " + boardId));
		Comment comment = request.toEntity(user, board);
		return commentRepository.save(comment);
	}

	public List<Comment> getComments(Long boardId) {
		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException("not found: " + boardId));
		return board.getCommentList();
	}

	public Comment getComment(Long commentId) {
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(()->new IllegalArgumentException("not found comment: " + commentId));
		return comment;
	}
	
	public void deleteComment(Long commentId) {
		commentRepository.deleteById(commentId);
	}

	@Transactional
	public Comment updateComment(Long commentId, UpdateCommentRequest request){
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("not found: " + commentId));
		comment.update(request.getContent());
		return comment;
	}
}
