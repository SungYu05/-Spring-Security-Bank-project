package shop.mtcoding.bank.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.bank.config.exception.CustomApiException;
import shop.mtcoding.bank.dto.ResponseDto;

@RestControllerAdvice // Exception 실행시 쓰는 어노테이션
public class CustomExceptionAdvice { // CustomExceptionHandler도 괜찮다.

    // @ExceptionHandler(CustomApiException.class)
    // public ResponseEntity<?> apiException(CustomApiException e) {
    // System.out.println("에러의 제어권을 잡음");
    // return new ResponseEntity<>(new ResponseDto<>(e.getHttpStatus(),
    // e.getMessage(), null));
    // // body에는 ResponseDto를 만드는 게 좋다. => 공통 Dto 만들기

    // }
    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException e) {
        System.out.println("에러의 제어권을 잡음");
        return new ResponseEntity<>(new ResponseDto<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
}
