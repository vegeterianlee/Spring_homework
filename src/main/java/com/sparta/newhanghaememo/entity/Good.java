package com.sparta.newhanghaememo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
@NoArgsConstructor
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "User_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Comment_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Comment comment;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Memo_ID")
    @OnDelete(action = OnDeleteAction.CASCADE) //상속된 쪽에서 따라서 삭제하기 위함
    private Memo memo;


    public Good( User user, Memo memo) {
        this.user = user;
        this.memo = memo;
    }

    public Good( User user, Comment comment) {
        this.user = user;
        this.comment = comment;
    }
    /*public void good_change (User user) { //객체 생성 후 수정하고 싶어서 메서드 생성
        this.good = false;

    }*/

}
