package com.sparta.hanghaememo.controller;


import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.dto.SuccessRequestDto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/request")
public class MemoController {

    private final MemoService memoService;

   /* @GetMapping("/") //메인페이지, ModelAndView라는 객체를 생성
    public ModelAndView home() {
        return new ModelAndView("index");
    }*/

    //저장

    @PostMapping("/api/post") //return값이 프론트엔드로 간다
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        return memoService.createMemo(requestDto);
    }

    //조회
    @GetMapping("/api/posts")
    public List<Memo> getMemos() {
        return memoService.getMemos();
    }

    @GetMapping("/api/post/{id}")
    public Memo getIdMemo(@PathVariable Long id) {
        return memoService.getIdMemo(id);
    }


    @PutMapping("/api/post/{id}") //@PathVariable
    public Memo updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        return memoService.update(id,requestDto);
    }

    @DeleteMapping("/api/post/{id}")
    public SuccessRequestDto deleteMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        return memoService.deleteMemo(id,requestDto);
    }

}