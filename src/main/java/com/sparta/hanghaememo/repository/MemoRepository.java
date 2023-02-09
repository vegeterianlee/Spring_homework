package com.sparta.hanghaememo.repository;


import com.sparta.hanghaememo.dto.MemoResponseDto;
import com.sparta.hanghaememo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByOrderByCreatedAtDesc();//작성날짜 기준 내림차순으로 정리

    Optional<Memo> findByIdAndPassword(Long id, String password);
}