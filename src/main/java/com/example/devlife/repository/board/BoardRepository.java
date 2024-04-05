package com.example.devlife.repository.board;

import com.example.devlife.entity.Board;
import com.example.devlife.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByCategory(Category category);

}
