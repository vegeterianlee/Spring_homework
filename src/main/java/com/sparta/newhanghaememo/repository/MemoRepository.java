package com.sparta.newhanghaememo.repository;


import com.sparta.newhanghaememo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByOrderByCreatedAtDesc();//생성일자 기준 내림차순 정리

    /*Optional<Memo> findByIdAndPassword(Long id, String password);*/

    Optional<Memo> findById(Long id);
    Optional<Memo> findByIdAndUserId(Long id, Long userId);

}
