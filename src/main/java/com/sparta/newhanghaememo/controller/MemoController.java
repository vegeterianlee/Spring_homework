package com.sparta.newhanghaememo.controller;

import com.sparta.newhanghaememo.dto.MemoRequestDto;
import com.sparta.newhanghaememo.dto.MemoResponseDto;
import com.sparta.newhanghaememo.security.UserDetailsImpl;
import com.sparta.newhanghaememo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;
    //저장
    @PostMapping("/api/post") //return값이 프론트엔드로 간다
    // 응답 보내기
    public ResponseEntity<?> createMemo(@RequestBody MemoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.createMemo(requestDto,userDetails.getUser());
    }


    //조회
    @GetMapping("/api/posts")
    public ResponseEntity<Map<String,Object>> getMemos() {
        return memoService.getMemos();
    }

    @GetMapping("/api/post/{id}")
    public MemoResponseDto getIdMemo(@PathVariable Long id) {
        return memoService.getIdMemo(id);
    }


    @PutMapping("/api/post/{id}") //@PathVariable, 여기서 @RequestBody로 MemoRequestDto가 오면서 데이터가 입력
    public ResponseEntity<?> updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.update(id,requestDto,userDetails.getUser());
    }

    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<?> deleteMemo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.deleteMemo(id,userDetails.getUser());
    }

}