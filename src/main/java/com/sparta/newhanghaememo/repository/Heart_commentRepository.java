package com.sparta.newhanghaememo.repository;

import com.sparta.newhanghaememo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Heart_commentRepository extends JpaRepository<Heart_comment, Long> {
    List<Heart_comment> findAll();
    //List<Heart_comment> findByCommentId(Long id);
    Optional<Heart_comment> findByCommentAndUser(Comment comment, User user);
}
