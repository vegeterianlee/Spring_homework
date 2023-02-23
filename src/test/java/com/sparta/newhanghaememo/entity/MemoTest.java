package com.sparta.newhanghaememo.entity;

import com.sparta.newhanghaememo.dto.MemoRequestDto;
import com.sparta.newhanghaememo.repository.MemoRepository;
import com.sparta.newhanghaememo.service.MemoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MemoTest {
    @Mock //모킹할 객체를 표기
    MemoRepository memoRepository;

    @InjectMocks //모킹한 객체를 주입해주는 코드
                 //이제 서비스에 있는 리포지토리는 가짜 객체가 들어가게 된다
    MemoService memoService;

    @Mock
    User user;

    private String title;
    private String content;

    @BeforeEach
    void beforeEach() {
        user = new User("jin123", "abcA!123", UserRoleEnum.USER);
        title = "title1";
        content = "content1";
    }

    @Test
    @DisplayName("게시글 작성")
    void createMemo() {

        //given
        MemoRequestDto memoRequestDto = new MemoRequestDto(title, content);

        //when
       Memo memo = new Memo(memoRequestDto, user);

        //then
        assertEquals(user, memo.getUser());
        assertEquals(title, memo.getTitle());
        assertEquals(content, memo.getContent());
        assertEquals(0, memo.getHeart_memos().size());
    }
}