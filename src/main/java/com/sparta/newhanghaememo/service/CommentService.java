package com.sparta.newhanghaememo.service;

import com.sparta.newhanghaememo.dto.CommentRequestDto;
import com.sparta.newhanghaememo.dto.CommentResponseDto;
import com.sparta.newhanghaememo.dto.SuccessResponseDto;
import com.sparta.newhanghaememo.entity.Comment;
import com.sparta.newhanghaememo.entity.Memo;
import com.sparta.newhanghaememo.entity.User;
import com.sparta.newhanghaememo.entity.UserRoleEnum;
import com.sparta.newhanghaememo.jwt.JwtUtil;
import com.sparta.newhanghaememo.repository.CommentRepository;
import com.sparta.newhanghaememo.repository.MemoRepository;
import com.sparta.newhanghaememo.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final MemoRepository memoRepository;
    public CommentResponseDto createComment(Long id, CommentRequestDto requestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 추가 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Memo memo = memoRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new IllegalArgumentException("일치하는 id의 게시글이 없습니다")
            );
                Comment comment = commentRepository.saveAndFlush(new Comment(requestDto, user,memo));
                return new CommentResponseDto(comment);
                
        } else {
            return null;
        }
    }

    @Transactional
    public CommentResponseDto update(Long id, CommentRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 추가 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 사용자 권한 가져와서 ADMIN 이면 전체 조회, USER 면 본인이 추가한 부분 조회
            UserRoleEnum userRoleEnum = user.getRole();
            if (userRoleEnum == UserRoleEnum.USER) {
                // 사용자 권한이 USER일 경우
                Comment comment = commentRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                        () -> new IllegalArgumentException("Userid나 코멘트id를 갖는 코멘토가 없습니다")
                );
                comment.update(requestDto);
                return new CommentResponseDto(comment);

            } else {
                Comment comment = commentRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("해당 id를 갖는 코멘트가 없습니다")
                );
                comment.update(requestDto);
                return new CommentResponseDto(comment);
            }
           /* //메모부터 조회하기

            if(comment.getUser().getUsername().equals(user.getUsername())){
                comment.update(requestDto);
                return new CommentResponseDto(comment);
            }*/
        }
         else {
            return null;
        }
    }

    @Transactional
    public SuccessResponseDto deleteComment(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 추가 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            UserRoleEnum userRoleEnum = user.getRole();
            if (userRoleEnum == UserRoleEnum.USER) {
                // 사용자 권한이 USER일 경우
                Comment comment = commentRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                        () -> new IllegalArgumentException("User id와 Memo id를 갖는 댓글이 없습니다")
                );
               commentRepository.deleteById(comment.getId());
                return new SuccessResponseDto("댓글 삭제 성공", 200);

            } else {
                Comment comment = commentRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("해당 id를 갖는 댓글이 없습니다")
                );
                commentRepository.deleteById(comment.getId());
                return new SuccessResponseDto("댓글 삭제 성공", 200);
            }

        } else {
            return null;
        }

    }
}
