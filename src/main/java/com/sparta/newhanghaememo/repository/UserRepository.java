package com.sparta.newhanghaememo.repository;

import com.sparta.newhanghaememo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    //User findByUsername(String username);
    //List<User> findAllByOrderByCreatedAtDesc();//생성일자 기준 내림차순 정리
}

