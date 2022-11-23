package shop.mtcoding.bank.config.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserEnum {
    ADMIN("관리자"), CUSTOMER("고객");
    // 일반적인 클래스의 경우 UserEnum ADMIN("관리자"), CUSTOMER("고객"); 이런식으로 생성자의 형태다

    private String value; // 생성자를 만들었다.

}
