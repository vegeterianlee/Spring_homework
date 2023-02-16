package com.sparta.newhanghaememo.controller;

import com.sparta.newhanghaememo.dto.MemoRequestDto;
import com.sparta.newhanghaememo.dto.MemoResponseDto;
import com.sparta.newhanghaememo.dto.SuccessResponseDto;
import com.sparta.newhanghaememo.jwt.JwtUtil;
import com.sparta.newhanghaememo.repository.UserRepository;
import com.sparta.newhanghaememo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    //저장
    @PostMapping("/api/post") //return값이 프론트엔드로 간다
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto, HttpServletRequest request) {
        return memoService.createMemo(requestDto,request);
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
    public MemoResponseDto updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto, HttpServletRequest request) {
        return memoService.update(id,requestDto,request);
    }

    @DeleteMapping("/api/post/{id}")
    public SuccessResponseDto deleteMemo(@PathVariable Long id, HttpServletRequest request) {
        return memoService.deleteMemo(id,request);
    }

}