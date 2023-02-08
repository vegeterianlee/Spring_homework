package com.sparta.hanghaememo.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Entity에서 구현한 걸 Dto로
@NoArgsConstructor
@Getter //선물 상자 클래스
public class SuccessRequestDto {
    private boolean success;
    public SuccessRequestDto(boolean bool){
        this.success = bool;
    }

}
