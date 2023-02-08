package com.sparta.hanghaememo.entity;

import com.sparta.hanghaememo.dto.MemoRequestDto;
import jakarta.persistence.*;
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
    public void update(MemoRequestDto memoRequestDto) {
        this.author = memoRequestDto.getAuthor();
        this.content = memoRequestDto.getContent();
        this.password = memoRequestDto.getPassword();
        this.title = memoRequestDto.getTitle();
    }
}