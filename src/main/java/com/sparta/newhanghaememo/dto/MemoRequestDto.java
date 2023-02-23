package com.sparta.newhanghaememo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter //선물 상자 클래스
//@Builder
@NoArgsConstructor
public class MemoRequestDto { //계층 간 데이터 전송을 위해 도메인 모델 대신 쓰이는 객체

    //private Long id;
    private String title;
    private String content;
   /* private String password;*/
     public MemoRequestDto(String title, String content){
       this.title = title;
       this.content = content;
     }

}
