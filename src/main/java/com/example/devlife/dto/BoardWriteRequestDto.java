package com.example.devlife.dto;

import com.example.devlife.entity.Board;
import com.example.devlife.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardWriteRequestDto {
    private Category category;
    private String title;
    private String content;

    public Board toEntity(){
        return Board.builder()
                .category(category)
                .title(title)
                .content(content)
                .build();
    }
}
