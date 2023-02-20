package com.sparta.newhanghaememo.controller;

import com.sparta.newhanghaememo.dto.CommentRequestDto;
import com.sparta.newhanghaememo.jwt.JwtUtil;
import com.sparta.newhanghaememo.repository.UserRepository;
import com.sparta.newhanghaememo.security.UserDetailsImpl;
import com.sparta.newhanghaememo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
private final JwtUtil jwtUtil;
    private final UserRepository userRepository;


    //저장
    @PostMapping("/api/comment/{id}") //return값이 프론트엔드로 간다
    public ResponseEntity<?> createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(id,requestDto,userDetails.getUser());
    }

    @PutMapping("/api/comment/{id}") //@PathVariable, 여기서 @RequestBody로 MemoRequestDto가 오면서 데이터가 입력
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.update(id,requestDto,userDetails.getUser());
    }

    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(id,userDetails.getUser());
    }
}
