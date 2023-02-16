package com.sparta.newhanghaememo.dto;

import com.sparta.newhanghaememo.entity.Comment;
import com.sparta.newhanghaememo.entity.Memo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class MemoResponseDto {

    private Long id;
    private String title;
    private String content;
    /*private String password;*/
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentList = new ArrayList<>();


    //entity ->dto
    public MemoResponseDto(Memo memo){
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.content = memo.getContent();
        this.username = memo.getUser().getUsername();
        /*this.password = memo.getPassword();*/
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();

        //새로 객체를 떠줘야 나오네->new를 적어줘야되니
        //메모에 comment를 one to many 시켜서 해당되는 comment만 나오게끔
        // ResponseDto를 무적권써야되네
        /*List<Comment>comments=memo.getCommentList();
        Collections.sort(comments, Collections.reverseOrder());
        for(Comment comment:comments){
            this.commentList.add(comment);
        }*/
        for (Comment comment: memo.getCommentList()){
            this.commentList.add(new CommentResponseDto(comment));
        }

    }

}
