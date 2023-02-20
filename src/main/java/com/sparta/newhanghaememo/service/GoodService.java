package com.sparta.newhanghaememo.service;

import com.sparta.newhanghaememo.dto.SuccessResponseDto;
import com.sparta.newhanghaememo.entity.Comment;
import com.sparta.newhanghaememo.entity.Good;
import com.sparta.newhanghaememo.entity.Memo;
import com.sparta.newhanghaememo.entity.User;
import com.sparta.newhanghaememo.repository.CommentRepository;
import com.sparta.newhanghaememo.repository.GoodRepository;
import com.sparta.newhanghaememo.repository.MemoRepository;
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
    private final GoodRepository goodRepository;

    @Transactional
    public ResponseEntity<?> good_memo(User user, Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 id의 메모가 없습니다")
        );
        Optional<Good> found = goodRepository.findByMemoAndUser(memo, user);
        if (found.isPresent()) {
            goodRepository.deleteById(found.get().getId());
            SuccessResponseDto successResponseDto = new SuccessResponseDto("메모에 좋아요 삭제", HttpStatus.OK.value());
            return ResponseEntity.ok().body(successResponseDto);
        } else {
            Good good = new Good(user, memo);
            goodRepository.save(good);
            SuccessResponseDto successResponseDto = new SuccessResponseDto("메모에 좋아요 등록", HttpStatus.OK.value());
            return ResponseEntity.ok().body(successResponseDto);
        }
    }

    @Transactional
    public ResponseEntity<?> good_comment(User user, Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 id의 코멘트가 없습니다")
        );

        Optional<Good> found = goodRepository.findByCommentAndUser(comment, user);
        if (found.isPresent()) {
            goodRepository.deleteById(found.get().getId());
            SuccessResponseDto successResponseDto = new SuccessResponseDto("코멘트에 좋아요 삭제", HttpStatus.OK.value());
            return ResponseEntity.ok().body(successResponseDto);
        } else {
            Good good = new Good(user, comment);
            goodRepository.save(good);
            SuccessResponseDto successResponseDto = new SuccessResponseDto("코멘트에 좋아요 등록", HttpStatus.OK.value());
            return ResponseEntity.ok().body(successResponseDto);
        }

    }

    public int heart_memo_count(Memo memo) {
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
    }
}