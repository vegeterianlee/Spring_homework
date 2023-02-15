package com.sparta.newhanghaememo.entity;

import com.sparta.newhanghaememo.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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

    @ManyToOne //many to one에서 외래키 설정해서 one쪽으로 이어진다 생각
    // 유저가 있어야 폴더가 생성된다는 느낌으로
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;


    public Memo(MemoRequestDto requestDto,User user) {
        this.content = requestDto.getContent();
        this.title = requestDto.getTitle();
        this.user = user;
    }
    public void update(MemoRequestDto memoRequestDto) { //객체 생성 후 수정하고 싶어서 메서드 생성
        this.content = memoRequestDto.getContent();
        this.title = memoRequestDto.getTitle();
    }
}
