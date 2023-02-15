package com.sparta.newhanghaememo.service;

import com.sparta.newhanghaememo.dto.MemoRequestDto;
import com.sparta.newhanghaememo.dto.MemoResponseDto;
import com.sparta.newhanghaememo.dto.SuccessResponseDto;
import com.sparta.newhanghaememo.entity.Memo;
import com.sparta.newhanghaememo.entity.User;
import com.sparta.newhanghaememo.jwt.JwtUtil;
import com.sparta.newhanghaememo.repository.MemoRepository;
import com.sparta.newhanghaememo.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    //데이터 베이스 연결과 저장
    public MemoResponseDto createMemo(MemoRequestDto requestDto, HttpServletRequest request) {
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

            // 요청받은 DTO 로 DB에 저장할 객체 만들기
            Memo memo = memoRepository.saveAndFlush(new Memo(requestDto, user));
            return new MemoResponseDto(memo);

        } else {
            return null;
        }
    }

    //조회: id없이 전부
    @Transactional(readOnly = true)
    public List<MemoResponseDto> getMemos() {
        ArrayList<MemoResponseDto> postList = new ArrayList<>();
        for (Memo memo : memoRepository.findAllByOrderByIdAsc()) {
            postList.add(new MemoResponseDto(memo));
        }
        return postList;
    }

    //조회: id 포함해서 하나
    @Transactional(readOnly = true)
    public MemoResponseDto getIdMemo(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 id가 없습니다")
        );
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
        return memoResponseDto;
    }

    @Transactional
    public MemoResponseDto update(Long id, MemoRequestDto requestDto, HttpServletRequest request) {
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

            //메모부터 조회하기
                Memo memo = memoRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("해당사용자가 아닙니다")
                );
                if(memo.getUser().getUsername()== user.getUsername()){
                    memo.update(requestDto);
                    return new MemoResponseDto(memo);
                }

            else{
                throw new IllegalArgumentException("User different");
            }

        } else {
            return null;
        }
    }

    @Transactional
    public SuccessResponseDto deleteMemo(Long id, HttpServletRequest request) {
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
            //메모부터 조회하기
            Memo memo = memoRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("해당사용자가 아닙니다")
            );
            if(memo.getUser().getUsername()== user.getUsername()){
                memoRepository.deleteById(memo.getId());
                return new SuccessResponseDto("게시글 삭제 성공", 200);
            }
            else{
                throw new IllegalArgumentException("User different");
            }

        } else {
            return null;
        }
    }
}

