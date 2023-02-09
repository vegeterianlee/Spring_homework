package com.sparta.hanghaememo.service;

import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.dto.MemoResponseDto;
import com.sparta.hanghaememo.dto.SuccessRequestDto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.repository.MemoRepository;
import org.apache.catalina.valves.HealthCheckValve;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    @Transactional
    //데이터 베이스 연결과 저장
    public MemoResponseDto createMemo(MemoRequestDto requestDto){
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
        return memoResponseDto;
    }

    //조회: id없이 전부
@Transactional(readOnly =  true)
    public List<MemoResponseDto> getMemos() {
    ArrayList<MemoResponseDto> list = new ArrayList<>();
        for(Memo memo:memoRepository.findAllByOrderByCreatedAtDesc()){
            list.add(new MemoResponseDto(memo));
    }
        return list;
    }

    //조회: id 포함해서 하나
 @Transactional(readOnly =  true)
 public MemoResponseDto getIdMemo(Long id){
     Memo memo = memoRepository.findById(id).orElseThrow(
             () -> new IllegalArgumentException("일치하는 id가 없습니다")
     );
     MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
     return memoResponseDto;
 }

@Transactional
    public MemoResponseDto update(Long id, MemoRequestDto requestDto) {

    String currentPassword = requestDto.getPassword();
        //메모부터 조회하기
    Memo memo = memoRepository.findByIdAndPassword(id,currentPassword).orElseThrow(
            ()-> new IllegalArgumentException("아이디 패스워드가 맞지 않습니다")
    );
        memo.update(requestDto);
    MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
    return memoResponseDto;
    }

    @Transactional
    public SuccessRequestDto deleteMemo(Long id , MemoRequestDto requestDto) {

        String currentPassword = requestDto.getPassword();
        //메모부터 조회하기
        Memo memo = memoRepository.findByIdAndPassword(id,currentPassword).orElseThrow(
                ()-> new IllegalArgumentException("아이디 패스워드가 맞지 않습니다")
        );
       memoRepository.deleteById(memo.getId());
       SuccessRequestDto successRequestDto = new SuccessRequestDto(true);
       return successRequestDto;
    }
}
