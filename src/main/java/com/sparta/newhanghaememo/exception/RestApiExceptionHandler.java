package com.sparta.newhanghaememo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //controller를 도와주는 경
public class RestApiExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<Object> handleApiRequestException(IllegalArgumentException ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setHttpStatus(HttpStatus.BAD_REQUEST);
        restApiException.setErrorMessage(ex.getMessage());
        restApiException.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity(
                restApiException,
                HttpStatus.BAD_REQUEST
        );
    }


}
