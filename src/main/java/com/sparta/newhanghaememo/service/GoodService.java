package com.sparta.newhanghaememo.service;

import com.sparta.newhanghaememo.dto.SuccessResponseDto;
import com.sparta.newhanghaememo.entity.*;
import com.sparta.newhanghaememo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoodService {
    private final MemoRepository memoRepository;
    private final CommentRepository commentRepository;
    private final Heart_memoRepository heartMemoRepository;
    private final Heart_commentRepository heartCommentRepository;

    @Transactional
    public ResponseEntity<?> heart_memo(User user, Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 id의 메모가 없습니다")
        );
        Optional<Heart_memo> found = heartMemoRepository.findByMemoAndUser(memo, user);
        if (found.isPresent()) {
            heartMemoRepository.deleteById(found.get().getId());
            SuccessResponseDto successResponseDto = new SuccessResponseDto("메모에 좋아요 삭제", HttpStatus.OK.value());
            return ResponseEntity.ok().body(successResponseDto);
        } else {
            Heart_memo heart_memo = new Heart_memo(user, memo);
            heartMemoRepository.save(heart_memo);
            SuccessResponseDto successResponseDto = new SuccessResponseDto("메모에 좋아요 등록", HttpStatus.OK.value());
            return ResponseEntity.ok().body(successResponseDto);
        }
    }

    @Transactional
    public ResponseEntity<?> heart_comment(User user, Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 id의 코멘트가 없습니다")
        );

        Optional<Heart_comment> found = heartCommentRepository.findByCommentAndUser(comment, user);
        if (found.isPresent()) {
            heartCommentRepository.deleteById(found.get().getId());
            SuccessResponseDto successResponseDto = new SuccessResponseDto("코멘트에 좋아요 삭제", HttpStatus.OK.value());
            return ResponseEntity.ok().body(successResponseDto);
        } else {
            Heart_comment heart_comment = new Heart_comment(user, comment);
            heartCommentRepository.save(heart_comment);
            SuccessResponseDto successResponseDto = new SuccessResponseDto("코멘트에 좋아요 등록", HttpStatus.OK.value());
            return ResponseEntity.ok().body(successResponseDto);
        }

    }

    /*public int heart_memo_count(Memo memo) {
        int count = 0;  // 좋아요 갯수 세기
            for (Good good : goodRepository.findByMemoId(memo.getId())) {
                    count++;
            }
        return count;
    }

    public int heart_comment_count(Comment comment) {
        int count = 0;  // 좋아요 갯수 세기
        for (Good good : goodRepository.findByCommentId(comment.getId())) {
                count++;
        }
        return count;
    }*/
}