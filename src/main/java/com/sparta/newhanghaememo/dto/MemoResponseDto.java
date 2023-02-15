package com.sparta.newhanghaememo.dto;

import com.sparta.newhanghaememo.entity.Memo;
import com.sparta.newhanghaememo.entity.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemoResponseDto extends Timestamped {

    private Long id;
    private String title;
    private String content;
    /*private String password;*/
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    //entity ->dto
    public MemoResponseDto(Memo memo){
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.content = memo.getContent();
        this.username = memo.getUser().getUsername();
        /*this.password = memo.getPassword();*/
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();
    }

}
