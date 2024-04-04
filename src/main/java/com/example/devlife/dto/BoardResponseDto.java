package com.example.devlife.dto;

import com.example.devlife.entity.Board;
import com.example.devlife.entity.Category;
import com.example.devlife.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private Category category;
    private String title;
    private String content;
    private int hit;
    private int boardLike;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User user;

    @Builder
    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.category = board.getCategory();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.hit = board.getHit();
        this.boardLike = board.getBoardLike();
        this.createdAt = board.getCreatedAt();
        this.updatedAt = board.getUpdatedAt();
        this.user = board.getUser();
    }
}
