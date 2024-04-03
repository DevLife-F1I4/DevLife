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

import jakarta.transaction.Transactional;

@Service
public class CommentService {
	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;
	public CommentService(CommentRepository commentRepository, BoardRepository boardRepository) {
		this.commentRepository = commentRepository;
		this.boardRepository = boardRepository;
	}

	public Comment save(User user, Long boardId, AddCommentRequest request) {
		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException("not found: " + boardId));
		return commentRepository.save(request.toEntity(user, board));
	}

	public List<Comment> getComments(Long boardId) {
		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException("not found: " + boardId));
		return board.getCommentList();
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
