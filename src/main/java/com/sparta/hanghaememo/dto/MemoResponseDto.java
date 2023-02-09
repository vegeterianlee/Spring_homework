package com.sparta.hanghaememo.dto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.entity.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MemoResponseDto extends Timestamped {

    private Long id;
    private String title;
    private String content;
    private String author;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    //entity ->dto
    public MemoResponseDto(Memo memo){
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.author = memo.getAuthor();
        this.content = memo.getContent();
        this.password = memo.getPassword();
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();
    }

}
