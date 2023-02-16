package com.sparta.newhanghaememo.repository;

import com.sparta.newhanghaememo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //List<Comment> findAllByOrderByCreatedAtDesc();

    Optional<Comment> findById(Long id);

    Optional<Comment> findByIdAndUserId(Long id, Long userId);

}
