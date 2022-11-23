package shop.mtcoding.bank.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass // 자식이 이 어노테이션을 상속할 건데, 자식이 이 어노테이션을 테이블에 컬럼으로 만든다.
@EntityListeners(AuditingEntityListener.class) // insert 시 자동으로 걸어준다
public abstract class AudingTime {
    // 날짜를 위한 class
    // 매번 만들기 귀찮아서 따로 만들어줌
    // 공통으로 만든 건 extends헤서 써야 한다
    // 하는 방법:
    // @getter → @EntityListeners(AuditingEntityListener.class) →
    // @MappedSuperclass → User class에 가서 extends를 걸기
    // → BankApplication에서 @EnableJpaAuditing 걸기

    // abstract를 걸어야 new를 걸지 않는다. abstract 거는 이유는 자식을 가져오기 위해서
    // Auding은 주입한다는 뜻이니까, 시간을 주입한다고 보면 된다.

    @LastModifiedBy // insert, update 시 현재시간이 들어간다
    @Column(nullable = false) // null값이 안 들어오게 만들어준다.
    protected LocalDateTime updateAt;
    // public으로 쓰지 않은 이유는 다른 데서 쓰지 않을 거라서.
    // protected 쓴 이유는 자식이 쓰기 위해서

    @CreatedDate // insert시 현재시간이 들어간다. // update시 변하지 않음
    @Column(nullable = false)
    protected LocalDateTime createdAt;

}
