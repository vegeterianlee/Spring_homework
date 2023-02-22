package com.sparta.newhanghaememo.repository;

import com.sparta.newhanghaememo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Heart_memoRepository extends JpaRepository<Heart_memo, Long> {
    List<Heart_memo> findAll();
    //List<Heart_memo> findByMemoId(Long id);
    Optional<Heart_memo> findByMemoAndUser(Memo memo, User user);

}
