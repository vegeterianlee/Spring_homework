package com.sparta.newhanghaememo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

//Entity에서 구현한 걸 Dto로
@NoArgsConstructor
@Getter //선물 상자 클래스
public class SuccessResponseDto<T> {
    private T msg;
    private int statusCode;
    public SuccessResponseDto(T msg, int statusCode)
    {
        this.msg = msg;
        this.statusCode=statusCode;
    }
    public static <T> SuccessResponseDto<T> ok(T result){
        return new SuccessResponseDto<>(result, HttpStatus.OK.value());
    }

    public static <T> SuccessResponseDto<T> fail(T result, int statusCode){
        return new SuccessResponseDto<>(result,statusCode);
    }
}
