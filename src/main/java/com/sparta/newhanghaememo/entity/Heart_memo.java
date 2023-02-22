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
public class Heart_memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "User_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Memo_ID")
    @OnDelete(action = OnDeleteAction.CASCADE) //상속된 쪽에서 따라서 삭제하기 위함
    private Memo memo;


    public Heart_memo( User user, Memo memo) {
        this.user = user;
        this.memo = memo;
    }
}
