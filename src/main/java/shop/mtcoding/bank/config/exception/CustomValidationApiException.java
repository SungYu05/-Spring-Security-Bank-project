package shop.mtcoding.bank.config.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomValidationApiException extends RuntimeException {

    private final HttpStatus httpStatus;
    // validation이 뜨면 무조건 상태코드가 400이라서 코드를 안 받아도 된다.
    // 하지만 강제로 400을 넣어도 된다.
    // CustomApiExceptio는 다양한 오류가 뜨기 때문에 상태코드를 정하지 않았다.
    private Map<String, String> errorMap;

    // msg = "유효성 검사 실패" => 고정이라서 msg대신 "유효성 검사 실패"를 적어준다.
    // 하지만 프론트에서 알기 위해서 Map<String, String> erroMap을 같이 걸어준다.
    public CustomValidationApiException(Map<String, String> errorMap) {
        super("유효성 검사 실패");
        this.errorMap = errorMap;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
