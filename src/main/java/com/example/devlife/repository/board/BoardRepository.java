package com.example.devlife.repository.board;

<<<<<<< HEAD
import com.example.devlife.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.devlife.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
>>>>>>> d34974ebbe28096649089313822ef4cde8433889
}
