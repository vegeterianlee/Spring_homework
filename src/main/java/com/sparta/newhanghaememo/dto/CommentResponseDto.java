package com.sparta.newhanghaememo.dto;

import com.sparta.newhanghaememo.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter //선물 상자 클래스
public class CommentResponseDto {
    private Long id;
    private String content;
    /*private String password;*/
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto (Comment comment){
        this.id = comment.getId();
        this.content=comment.getContent();
        this.username=comment.getUser().getUsername();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
