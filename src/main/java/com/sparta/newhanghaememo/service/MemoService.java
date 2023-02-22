package com.sparta.newhanghaememo.service;

import com.sparta.newhanghaememo.dto.MemoRequestDto;
import com.sparta.newhanghaememo.dto.MemoResponseDto;
import com.sparta.newhanghaememo.dto.SuccessResponseDto;
import com.sparta.newhanghaememo.entity.Memo;
import com.sparta.newhanghaememo.entity.User;
import com.sparta.newhanghaememo.entity.UserRoleEnum;
import com.sparta.newhanghaememo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;
    private final GoodService goodService;

    @Transactional
    //데이터 베이스 연결과 저장
    public ResponseEntity<?> createMemo(MemoRequestDto requestDto, User user) {
        // Request에서 Token 가져오기
        /*String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 추가 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                *//*SuccessResponseDto successResponseDto = new SuccessResponseDto("토큰이 유효하지 않습니다.", 400);
                return new ResponseEntity<SuccessResponseDto>(successResponseDto, HttpStatus.BAD_REQUEST);*//*
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            *//*User user = userRepository.findByUsername(claims.getSubject());
            if (user != null) {
                SuccessResponseDto successResponseDto = new SuccessResponseDto("사용자가 존재하지 않습니다.", 400);
                return new ResponseEntity<SuccessResponseDto>(successResponseDto, HttpStatus.BAD_REQUEST);
                //throw new IllegalArgumentException("사용자가 존재하지 않습니다.");
            }*//*

            // 요청받은 DTO 로 DB에 저장할 객체 만들기
            Memo memo = memoRepository.saveAndFlush(new Memo(requestDto, user));
            MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
            return new ResponseEntity<MemoResponseDto>(memoResponseDto, HttpStatus.OK);

        } else {
            return null;
        }*/
        Memo memo = memoRepository.save(new Memo(requestDto, user));
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
        return ResponseEntity.ok().body(memoResponseDto);
        //return new ResponseEntity<MemoResponseDto>(memoResponseDto, HttpStatus.OK);
    }

    //조회: id없이 전부
    //@Secured("ROLE_ADMIN")
    @Transactional(readOnly = true)
    public ResponseEntity<Map<String, Object>> getMemos() {
        ArrayList<MemoResponseDto> list = new ArrayList<>();
        for (Memo memo : memoRepository.findAllByOrderByCreatedAtDesc()) {
            list.add(new MemoResponseDto(memo));
        }

        Map<String, Object> result = new HashMap<>();
        result.put("postList", list);
        return ResponseEntity.ok().body(result);
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
    public ResponseEntity<?> update(Long id, MemoRequestDto requestDto, User user) {
        UserRoleEnum userRoleEnum = user.getRole();
        if (userRoleEnum == UserRoleEnum.USER) {
            // 사용자 권한이 USER일 경우
            Memo memo = memoRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new IllegalArgumentException("해당 User id를 갖는 유저의 Memo id의 memo가 없습니다")
            );
            memo.update(requestDto);
            MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
            return ResponseEntity.ok().body(memoResponseDto); //(msg=body)
            // return new ResponseEntity<MemoResponseDto>(memoResponseDto,HttpStatus.OK);

        } else {
            Memo memo = memoRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("해당 id를 갖는 memo가 없습니다")
            );
            memo.update(requestDto);
            MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
            return ResponseEntity.ok().body(memoResponseDto);
            //return new ResponseEntity<MemoResponseDto>(memoResponseDto,HttpStatus.OK);
        }
    }


    @Transactional
    public ResponseEntity<?> deleteMemo(Long id, User user) {
            UserRoleEnum userRoleEnum = user.getRole();
            if (userRoleEnum == UserRoleEnum.USER) {
                // 사용자 권한이 USER일 경우
                Memo memo = memoRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                        () -> new IllegalArgumentException("해당 User id를 갖는 유저의 Memo id의 memo가 없습니다")
                );
                memoRepository.deleteById(memo.getId());
                SuccessResponseDto successResponseDto =new SuccessResponseDto("게시글 삭제 성공",HttpStatus.OK.value());
                return ResponseEntity.ok().body(successResponseDto);
                //return new SuccessResponseDto("게시글 삭제 성공", 200);

            } else {
                Memo memo = memoRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("해당 id를 갖는 memo가 없습니다")
                );
                memoRepository.deleteById(memo.getId());
                SuccessResponseDto successResponseDto =new SuccessResponseDto("게시글 삭제 성공",HttpStatus.OK.value());
                return ResponseEntity.ok().body(successResponseDto);
                //return new ResponseEntity<SuccessResponseDto>(successResponseDto, HttpStatus.OK);
            }
    }
}