package shop.mtcoding.bank.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // extends JpaRepository를 하고 <User, Long> 이렇게 타입 걸면 기본crud 다 걸어준다.
    // 비슷한 프레임워크: Django (admin 페이지를 자동으로 만들어주기 때문에 관리자를 내가 만들지 않아도 된다.)
    // 왜 프레임워크를 만들까? 많이 쓰면 무엇이 좋기 때문에?
    // 내가 생각한 답: 시간을 단축시키면 그만큼 효율적으로 일할 수 있기 때문에

}
