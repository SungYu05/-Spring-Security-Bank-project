package shop.mtcoding.bank.config.exception;

import lombok.Getter;

@Getter // 넣기만 가능하고, 수정은 못하기 때문에 @getter만 사용 (@setter 사용x, 빈생성자 만들지 않기)
public class CustomApiException extends RuntimeException {

    private final int httpStatusCode; // 한번 걸면 변경할 일이 없기 때문에 final을 걸었다.

    public CustomApiException(String msg, int httpStatusCode) {
        super(msg);
        this.httpStatusCode = httpStatusCode;

        // getMsg 하면 메시지가 나오게 되어 있다.
    }
}
