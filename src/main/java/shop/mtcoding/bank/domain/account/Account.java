package shop.mtcoding.bank.domain.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.bank.domain.AudingTime;
import shop.mtcoding.bank.domain.user.User;

@NoArgsConstructor
@Getter
@Table(name = "account")
@Entity
public class Account extends AudingTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private Long number; // 계좌 // 계좌는 유니크해야 한다.

    @Column(nullable = false, length = 50)
    private String ownerName; // 계좌주 실명 // 이름은 공통될 수 있다.

    @Column(nullable = false, length = 4)
    private String password;

    @Column(nullable = false)
    private Long balance; // 잔액 // 단위가 커질 수 있기 때문에 int가 아니라 long을 사용한다.

    // 커멜케이스는 DB 언더스코어로 생성된다.
    // 하지만 지금은 하이버네이트에서 안 걸어줬기 때문에 언더스코어로 쓰인다.
    @Column(nullable = false)
    private Boolean isActive; // 계좌 활성화 여부

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    // enum을 쓰지 않은 이유? true, false 만 쓰면 되기 때문에
    // user 1: account N의 1:N 관계이기 때문에 f.k를 가져야 한다.
    // 이렇게 만들면 항상 user를 join하기 때문에 account만 보고 싶을 때 활용할 수 없다.
    // 제약권을 나한테 따로 줘야 한다. 그때 사용하는 어노테이션이
    // @ManyToOne에 (fetch = FetchType.LAZY)의 제약조건을 건다
    // @ManyToOne에 (fetch = FetchType.LAZY)의 제약조건을 건다
    // 쓸 데 없는 연산을 막기 위해 사용

    // private List<Transaction> withdrawAccountBalance;
    // private List<Transaction> depositAccountBalance;
    // db에는 내역이 들어가지만 account 영속성 객체에는 없다가, 응답이 완전히 끝나고 클라이언트가 account를 다시 부를 때
    // 튀어나온다.
    // account select 하고 이체한 시점(트랜잭션 종료 전)에는 안 들어가서 강제로 채워야 한다. (순수하게 채워지지 않음)
    // 이렇게 동기화되지 않음
    // 이렇게 보이지 않는 것을 팬텀(유령)이라고 하는데 그럼 내가 add를 넣어줘야 해서 양방향 mapping을 쓰지 않는다.

    @Builder
    public Account(Long id, Long number, String ownerName, String password, Long balance, Boolean isActive, User user) {
        this.id = id;
        this.number = number;
        this.ownerName = ownerName;
        this.password = password;
        this.balance = balance;
        this.isActive = isActive;
        this.user = user;
    }

}
