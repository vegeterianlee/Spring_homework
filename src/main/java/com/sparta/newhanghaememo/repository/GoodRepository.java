package com.sparta.newhanghaememo.repository;

import com.sparta.newhanghaememo.entity.Comment;
import com.sparta.newhanghaememo.entity.Good;
import com.sparta.newhanghaememo.entity.Memo;
import com.sparta.newhanghaememo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoodRepository extends JpaRepository<Good, Long> {
    List<Good> findAll();
    List<Good> findByMemoId(Long id);
    List<Good> findByCommentId(Long id);
    Optional<Good> findByMemoAndUser(Memo memo, User user);
    Optional<Good> findByCommentAndUser(Comment comment, User user);
}
