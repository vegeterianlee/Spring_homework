package com.sparta.newhanghaememo.repository;


import com.sparta.newhanghaememo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByOrderByIdAsc();//ID 기준 오름차순으로 정리

    /*Optional<Memo> findByIdAndPassword(Long id, String password);*/

    Optional<Memo> findById(Long id);
}
