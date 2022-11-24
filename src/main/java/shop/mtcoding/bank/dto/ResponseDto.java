package shop.mtcoding.bank.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// http code = 200(get, delete, put), 201(post)
// 200(select, delete 잘 됐을 때), 201(insert 잘 됐을 때)
// post를 제외한 모든 요청은 200! 단, 로그인 요청은 post지만 200으로 봐야 한다.
@Getter
@RequiredArgsConstructor
// 변경될 일이 없기 때문에 @setter를 만들지 않아도 된다.
public class ResponseDto<T> {
    // private final Integer code; 더이상 이건 dto에 쓰지 않는다.
    private final String msg;
    private final T data;

    // 한번 걸면 변경될 일이 없기 때문에 finla을 붙이고 @RequiredArgsConstructor 사용
}
