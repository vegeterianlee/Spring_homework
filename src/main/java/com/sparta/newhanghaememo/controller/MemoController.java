package com.sparta.newhanghaememo.controller;

import com.sparta.newhanghaememo.dto.MemoRequestDto;
import com.sparta.newhanghaememo.dto.MemoResponseDto;
import com.sparta.newhanghaememo.dto.SuccessResponseDto;
import com.sparta.newhanghaememo.security.UserDetailsImpl;
import com.sparta.newhanghaememo.service.MemoService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;
@Api(tags = {"게시글 API 정보를 제공하는 Controller"})
@RestController
//@RequestMapping("/api")
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;
    //저장
    @ApiOperation(value = "게시글 생성", notes = "신규 게시글을 생성한다."
            , produces = "application/json"
            , response = MemoResponseDto.class)
    /*@ApiImplicitParams({
            @ApiImplicitParam(
                    name = "MemoRequestDTO"
                    , value = "생성을 위해 필요한 Request Body"
            )
    })*/
    @ApiResponses({
            @ApiResponse(
                    code = 200
                    , message = "생성 성공"
                    , response = MemoResponseDto.class
                    , responseContainer = "Memo"     //list로 적혀있었음
            )
            , @ApiResponse(
            code = 400
            , message = "토큰이 유효하지 않습니다"
            , response = SuccessResponseDto.class
            , responseContainer = "msg"
    )
    })
    @PostMapping("/api/post") //return값이 프론트엔드로 간다
    // 응답 보내기
    public ResponseEntity<?> createMemo(@RequestBody MemoRequestDto requestDto,@ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.createMemo(requestDto,userDetails.getUser());
    }


    //조회
    @ApiOperation(value = "게시글 조회 메소드")
    /*@ApiImplicitParams(
            {@ApiImplicitParam(
                            name = "id"
                            , value = "memo 아이디"
                            , required = true
                            , dataType = "Long"
                            , paramType = "path"
                            , example = "1"
                            , defaultValue = "1"
                    ),
                    @ApiImplicitParam(
                            name = "Memo"
                            , value = "memo_entity"
                            , required = true
                            , dataType = "string"
                            , paramType = "query"
                            , defaultValue = "Null"
                    )
            })*/
    @ApiResponses({
            @ApiResponse(
                    code = 200
                    , message = "게시글 조회 성공"
                    , response = MemoResponseDto.class
                    , responseContainer = "Memo"     //list로 적혀있었음
            )
            , @ApiResponse(
            code = 401
            , message = "토큰이 유효하지 않습니다"
            , response = SuccessResponseDto.class
            , responseContainer = "msg"
    )
    })
    @GetMapping("/api/board")
    public ResponseEntity<Map<String,Object>> getMemos() {
        return memoService.getMemos();
    }

    @ApiOperation(value = "특정 게시글 조회 메소드")
    @GetMapping("/api/board/{id}")
    public MemoResponseDto getIdMemo(@PathVariable Long id) {
        return memoService.getIdMemo(id);
    }

    @ApiOperation(value = "특정 게시글 수정 메소드")
    @PutMapping("/api/post/{id}") //@PathVariable, 여기서 @RequestBody로 MemoRequestDto가 오면서 데이터가 입력
    public SuccessResponseDto<MemoResponseDto> updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto, @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.update(id,requestDto,userDetails.getUser());
    }

    @ApiOperation(value = "특정 게시글 삭제 메소드")
    @DeleteMapping("/api/post/{id}")
    public SuccessResponseDto<String> deleteMemo(@PathVariable Long id,@ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoService.deleteMemo(id,userDetails.getUser());
    }

}