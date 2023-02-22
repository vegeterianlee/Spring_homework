package com.sparta.newhanghaememo.entity;

import com.sparta.newhanghaememo.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;


@Getter
@Entity
@NoArgsConstructor

public class Memo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = LAZY) //many to one에서 외래키 설정해서 one쪽으로 이어진다 생각
    // 유저가 있어야 폴더가 생성된다는 느낌으로
    @JoinColumn(name = "USER_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToMany(mappedBy = "memo", fetch = FetchType.LAZY)
    // comment->memo 로 맵핑이 된다, 부모에 cascade적어도 되고 자식에 Ondelete해도 되고
    List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "memo", fetch = FetchType.LAZY)
    List<Heart_memo> heart_memos = new ArrayList<>();

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
