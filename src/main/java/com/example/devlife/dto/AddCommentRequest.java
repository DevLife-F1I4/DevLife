package com.example.devlife.dto;

import com.example.devlife.entity.Board;
import com.example.devlife.entity.Comment;
import com.example.devlife.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@NoArgsConstructor
@Getter
@AllArgsConstructor
public class AddCommentRequest {
	private String content;
	private Long parentId;
	private Long depth;
	private Long sequence;
	


	public Comment toEntity(User user, Board board) {
		return Comment.builder()
			.content(content)
			.parentId(parentId)
			.depth(depth)
			.sequence(sequence)
			.user(user)
			.board(board)
			.build();
	}

}
