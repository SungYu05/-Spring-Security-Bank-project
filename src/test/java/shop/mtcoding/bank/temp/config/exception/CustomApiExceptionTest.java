package shop.mtcoding.bank.temp.config.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import shop.mtcoding.bank.config.exception.CustomApiException;

@Getter // 넣기만 가능하고, 수정은 못하기 때문에 @getter만 사용 (@setter 사용x, 빈생성자 만들지 않기)
public class CustomApiExceptionTest {

    @Test
    public void customApi_test() throws Exception {
        // given
        String msg = "해당 id가 없습니다";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        // When
        CustomApiException ex = new CustomApiException(msg, httpStatus);
        System.out.println(ex.getHttpStatus());
        System.out.println(ex.getHttpStatus().value());
        System.out.println(ex.getMessage());

        // Then
        assertThat(ex.getHttpStatus().value()).isEqualTo(400);
        assertThat(ex.getMessage()).isEqualTo("해당 id가 없습니다");
    }

}
