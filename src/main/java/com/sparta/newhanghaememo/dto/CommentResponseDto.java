package com.sparta.newhanghaememo.dto;

import com.sparta.newhanghaememo.entity.Comment;
import com.sparta.newhanghaememo.entity.Heart_comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter //선물 상자 클래스
public class CommentResponseDto {
    private Long id;
    private String content;
    /*private String password;*/
    private String username;
    private int heart_count;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public CommentResponseDto (Comment comment){
        this.id = comment.getId();
        this.content=comment.getContent();
        this.username=comment.getUser().getUsername();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();

        List<Heart_comment> heart_comments=new ArrayList<>();
        for(Heart_comment heart_comment : comment.getHeart_comments()){
            if(comment.getId().equals(heart_comment.getComment().getId())){
                heart_comments.add(heart_comment);
            }
        }
        this.heart_count=heart_comments.size();
    }
}
