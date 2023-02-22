package com.sparta.newhanghaememo.controller;

import com.sparta.newhanghaememo.dto.MemoRequestDto;
import com.sparta.newhanghaememo.dto.MemoResponseDto;
import com.sparta.newhanghaememo.security.UserDetailsImpl;
import com.sparta.newhanghaememo.service.MemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@Api(tags = {"게시글 API 정보를 제공하는 Controller"})
@RestController
//@RequestMapping("/api")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;
    //저장
    @ApiOperation(value = "게시글 생성 메소드")
    @PostMapping("/api/post") //return값이 프론트엔드로 간다
    // 응답 보내기
    public ResponseEntity<?> createMemo(@RequestBody MemoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.createMemo(requestDto,userDetails.getUser());
    }


    //조회
    @ApiOperation(value = "게시글 조회 메소드")
    @ApiImplicitParam(name = "getMemos", value = "모든 게시글,댓글,좋아요 수를 담은 리스트", dataType = "list")
    @GetMapping("/api/posts")
    public ResponseEntity<Map<String,Object>> getMemos() {
        return memoService.getMemos();
    }

    @ApiOperation(value = "특정 게시글 조회 메소드")
    @ApiImplicitParam(name = "getMemo", value = "특정 게시글,댓글,좋아요 수를 담은 리스트", dataType = "MemoResponseDto")
    @GetMapping("/api/post/{id}")
    public MemoResponseDto getIdMemo(@PathVariable Long id) {
        return memoService.getIdMemo(id);
    }

    @ApiOperation(value = "특정 게시글 수정 메소드")
    @ApiImplicitParam(name = "updateMemo", value = "특정 게시글을 수정 후 조회 오류 시 원인과 statusCode 반환", dataType = "ResponseEntity<?>")
    @PutMapping("/api/post/{id}") //@PathVariable, 여기서 @RequestBody로 MemoRequestDto가 오면서 데이터가 입력
    public ResponseEntity<?> updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.update(id,requestDto,userDetails.getUser());
    }

    @ApiOperation(value = "특정 게시글 삭제 메소드")
    @ApiImplicitParam(name = "deleteMemo", value = "특정 게시글을 삭제, 성공이나 실패 시 원인과 statusCode 반환", dataType = "ResponseEntity<?>")
    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<?> deleteMemo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.deleteMemo(id,userDetails.getUser());
    }

}