package com.sparta.newhanghaememo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    @Size(min=4, max=10)
    @Pattern(regexp = "[a-z0-9]+",message = "최소 4자 이상, 10자 이하이며 알파벳 소문자,숫자(0~9)로 구성" )
    private String username;

    @Column(nullable = false)
    @Size(min=8, max=15)
    @Pattern(regexp = "[a-zA-Z0-9`~!@#$%^&*()-_=+]+",message = "최소 8자 이상, 15자 이하이며 알파벳 대,소문자,숫자(0~9)로 구성" )
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;
    @OneToMany(mappedBy = "user" )  //각각 memo의 user와
    List<Memo> postList = new ArrayList<>();

    @OneToMany(mappedBy = "user") // comment의 user로 맵핑이 된다
    List<Comment> commentList = new ArrayList<>();


    public User(String username, String password,UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /*public void addComment(Comment comment) {
        this.commentList.add(comment);
    }*/

}

