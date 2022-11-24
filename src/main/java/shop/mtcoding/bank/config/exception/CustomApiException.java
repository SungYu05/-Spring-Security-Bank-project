package shop.mtcoding.bank.config.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter // 넣기만 가능하고, 수정은 못하기 때문에 @getter만 사용 (@setter 사용x, 빈생성자 만들지 않기)
public class CustomApiException extends RuntimeException {

    private final HttpStatus httpStatus; // 한번 걸면 변경할 일이 없기 때문에 final을 걸었다.

    public CustomApiException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;

        // getMsg 하면 메시지가 나오게 되어 있다.
    }
}
