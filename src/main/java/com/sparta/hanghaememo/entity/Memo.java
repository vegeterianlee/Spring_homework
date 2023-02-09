package com.sparta.hanghaememo.entity;

import com.sparta.hanghaememo.dto.MemoRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@Entity
@NoArgsConstructor

public class Memo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String password;

    public Memo(MemoRequestDto requestDto) {
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
    }
    public void update(MemoRequestDto memoRequestDto) { //객체 생성 후 수정하고 싶어서 메서드 생성
        this.author = memoRequestDto.getAuthor();
        this.content = memoRequestDto.getContent();
        this.password = memoRequestDto.getPassword();
        this.title = memoRequestDto.getTitle();
    }
}