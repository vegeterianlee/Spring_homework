package com.sparta.newhanghaememo.exception;

import com.sparta.newhanghaememo.dto.SuccessResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //controller를 도와주는 경찰
public class RestApiExceptionHandler {

    @ExceptionHandler(NotFoundMemberException.class)
    public ResponseEntity<SuccessResponseDto> notFoundMember() {
        return new ResponseEntity<>(new SuccessResponseDto("회원을 찾을 수 없습니다.", 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicatMemberException.class)
    public ResponseEntity<SuccessResponseDto> duplicateMember() {
        return new ResponseEntity<>(new SuccessResponseDto("중복된 username 입니다.", 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<SuccessResponseDto> invalidToken() {
        return new ResponseEntity<>(new SuccessResponseDto("토큰이 유효하지 않습니다.", 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PermissionException.class)
    public ResponseEntity<SuccessResponseDto> permission() {
        return new ResponseEntity<>(new SuccessResponseDto("작성자만 삭제/수정 할 수 있습니다.", 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundContentsException.class)
    public ResponseEntity<SuccessResponseDto> notFoundContents() {
        return new ResponseEntity<>(new SuccessResponseDto("해당 목록을 찾을 수 없습니다.", 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<SuccessResponseDto> methodArgumentNotValid() {
        return new ResponseEntity<>(new SuccessResponseDto("아이디와 비밀번호 형식이 잘못되었습니다.", 400), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<SuccessResponseDto> illegalArgumentException(IllegalArgumentException ex){
       return new ResponseEntity<>(new SuccessResponseDto(ex.getMessage(), 400),HttpStatus.BAD_REQUEST);
    }

    /*@ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<Object> handleApiRequestException(IllegalArgumentException ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setHttpStatus(HttpStatus.BAD_REQUEST);
        restApiException.setErrorMessage(ex.getMessage());
        restApiException.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity(
                restApiException,
                HttpStatus.BAD_REQUEST
        );
    }*/


}
