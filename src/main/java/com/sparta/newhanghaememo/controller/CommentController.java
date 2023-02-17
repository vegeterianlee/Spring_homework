package com.sparta.newhanghaememo.controller;

import com.sparta.newhanghaememo.dto.CommentRequestDto;
import com.sparta.newhanghaememo.jwt.JwtUtil;
import com.sparta.newhanghaememo.repository.UserRepository;
import com.sparta.newhanghaememo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
private final JwtUtil jwtUtil;
    private final UserRepository userRepository;


    //저장
    @PostMapping("/api/comment/{id}") //return값이 프론트엔드로 간다
    public ResponseEntity<?> createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request) {
        return commentService.createComment(id,requestDto,request);
    }

    @PutMapping("/api/comment/{id}") //@PathVariable, 여기서 @RequestBody로 MemoRequestDto가 오면서 데이터가 입력
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request) {
        return commentService.update(id,requestDto,request);
    }

    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id, HttpServletRequest request) {
        return commentService.deleteComment(id,request);
    }
}
