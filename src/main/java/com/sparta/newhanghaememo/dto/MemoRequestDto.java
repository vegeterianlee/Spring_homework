package com.sparta.newhanghaememo.dto;

import lombok.Getter;

@Getter //선물 상자 클래스
//@Builder
public class MemoRequestDto { //계층 간 데이터 전송을 위해 도메인 모델 대신 쓰이는 객체

    //private Long id;
    private String title;
    private String content;
   /* private String password;*/

    //postman에서 데이터 넣으면 알아서 Dto로 저장되는 이유?

    /*public static MemoRequestDto of(Memo memo){
        return MemoRequestDto.builder()
                .id(memo.getId())
                .title(memo.getTitle())
                .author(memo.getAuthor())
                .content(memo.getContent())
                .password(memo.getPassword())
                .build();
    }*/
}
