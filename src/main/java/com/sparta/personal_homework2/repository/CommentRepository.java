package com.sparta.personal_homework2.repository;

import com.sparta.personal_homework2.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}