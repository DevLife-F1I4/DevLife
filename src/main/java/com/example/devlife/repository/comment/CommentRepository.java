package com.example.devlife.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.devlife.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
