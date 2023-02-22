package com.sparta.newhanghaememo.service;

import com.sparta.newhanghaememo.dto.CommentRequestDto;
import com.sparta.newhanghaememo.dto.CommentResponseDto;
import com.sparta.newhanghaememo.dto.SuccessResponseDto;
import com.sparta.newhanghaememo.entity.Comment;
import com.sparta.newhanghaememo.entity.Memo;
import com.sparta.newhanghaememo.entity.User;
import com.sparta.newhanghaememo.entity.UserRoleEnum;
import com.sparta.newhanghaememo.repository.CommentRepository;
import com.sparta.newhanghaememo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemoRepository memoRepository;
    public ResponseEntity<?> createComment(Long id, CommentRequestDto requestDto, User user) {

        //게시글이 아예없을 때 오류 발생
        Memo memo = memoRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                () -> new IllegalArgumentException("일치하는 id의 게시글이 없습니다")
        );
        Comment comment = commentRepository.save(new Comment(requestDto, user,memo));
        CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
        return new ResponseEntity<CommentResponseDto>(commentResponseDto, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> update(Long id, CommentRequestDto requestDto, User user) {
        UserRoleEnum userRoleEnum = user.getRole();
        if (userRoleEnum == UserRoleEnum.USER) {
            // 사용자 권한이 USER일 경우
            Comment comment = commentRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new IllegalArgumentException("Userid나 코멘트id를 갖는 코멘토가 없습니다")
            );
            comment.update(requestDto);
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            return new ResponseEntity<CommentResponseDto>(commentResponseDto, HttpStatus.OK);

        } else {
            Comment comment = commentRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("해당 id를 갖는 코멘트가 없습니다")
            );
            comment.update(requestDto);
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            return new ResponseEntity<CommentResponseDto>(commentResponseDto, HttpStatus.OK);
        }
    }

    @Transactional
    public ResponseEntity<?> deleteComment(Long id, User user) {
        UserRoleEnum userRoleEnum = user.getRole();
        if (userRoleEnum == UserRoleEnum.USER) {
            // 사용자 권한이 USER일 경우
            Comment comment = commentRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new IllegalArgumentException("User id와 Memo id를 갖는 댓글이 없습니다")
            );
            commentRepository.deleteById(comment.getId());
            SuccessResponseDto successResponseDto =new SuccessResponseDto("댓글 삭제 성공",200);
            return new ResponseEntity<SuccessResponseDto>(successResponseDto, HttpStatus.OK);

        } else {
            Comment comment = commentRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("해당 id를 갖는 댓글이 없습니다")
            );
            commentRepository.deleteById(comment.getId());
            SuccessResponseDto successResponseDto =new SuccessResponseDto("댓글 삭제 성공",200);
            return new ResponseEntity<SuccessResponseDto>(successResponseDto, HttpStatus.OK);
        }
    }
}
