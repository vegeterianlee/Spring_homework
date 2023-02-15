package com.sparta.newhanghaememo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

//Entity에서 구현한 걸 Dto로
@NoArgsConstructor
@Getter //선물 상자 클래스
public class SuccessResponseDto {
    private String msg;
    private int statusCode;
    public SuccessResponseDto(String msg, int statusCode)
    {
        this.msg = msg;
        this.statusCode=statusCode;
    }

}
