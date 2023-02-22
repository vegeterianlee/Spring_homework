package com.sparta.newhanghaememo.entity;

import com.sparta.newhanghaememo.dto.CommentRequestDto;
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
public class Comment extends Timestamped {
    @Id //테이블 단위별로 알아서 증가
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = LAZY) //many to one에서 외래키 설정해서 one쪽으로 이어진다 생각
    // 유저가 있어야 폴더가 생성된다는 느낌으로
    @JoinColumn(name = "USER_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = LAZY) //memo에 연결해줘야 해당된 게시글에만 comment가 쌓일 수 있도록
    @JoinColumn(name = "Memo_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Memo memo;

    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
    List<Heart_comment> heart_comments = new ArrayList<>();

    public Comment(CommentRequestDto requestDto, User user, Memo memo) {
        this.content = requestDto.getContent();
        this.user = user;
        this.memo = memo;
    }

    public void update(CommentRequestDto commentRequestDto) { //객체 생성 후 수정하고 싶어서 메서드 생성
        this.content = commentRequestDto.getContent();
    }
}
