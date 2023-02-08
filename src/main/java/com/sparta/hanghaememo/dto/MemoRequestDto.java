package com.sparta.hanghaememo.dto;
import lombok.Getter;


@Getter //선물 상자 클래스
public class MemoRequestDto { //계층 간 데이터 전송을 위해 도메인 모델 대신 쓰이는 객체
    private String title;
    private String content;
    private String author;
    private String password;
}
