package com.sparta.newhanghaememo.controller;

import com.sparta.newhanghaememo.dto.CommentRequestDto;
import com.sparta.newhanghaememo.security.UserDetailsImpl;
import com.sparta.newhanghaememo.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@Api(tags = {"댓글 API 정보를 제공하는 Controller"})
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //저장
    @ApiOperation(value = "Comment", notes = "댓글 저장")
    @ApiImplicitParam(name = "createComment", value = "title,comment", dataType = "ResponseEntity<?>")
    @PostMapping("/api/comment/{id}") //return값이 프론트엔드로 간다
    public ResponseEntity<?> createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(id,requestDto,userDetails.getUser());
    }
    @ApiOperation(value = "Comment" , notes = "댓글 수정 메소드")
    @ApiImplicitParam(name = "updateComment", value = "title,comment", dataType = "ResponseEntity<?>")
    @PutMapping("/api/comment/{id}") //@PathVariable, 여기서 @RequestBody로 MemoRequestDto가 오면서 데이터가 입력
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.update(id,requestDto,userDetails.getUser());
    }
    @ApiOperation(value = "SuccessResponseDto", notes = "댓글 삭제 메소드")
    @ApiImplicitParam(name = "deleteComment", dataType = "ResponseEntity<?>")
    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(id,userDetails.getUser());
    }
}
