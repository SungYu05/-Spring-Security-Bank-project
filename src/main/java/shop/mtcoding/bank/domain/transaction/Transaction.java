package shop.mtcoding.bank.domain.transaction;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.bank.config.enums.TransactionEnum;
import shop.mtcoding.bank.domain.account.Account;

@NoArgsConstructor
@Getter
@Table(name = "transaction")
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private Account withdrawAccount; // 출금 계좌 (보내는 분)

    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private Account depositAccount; // 입금 계좌 (받는 분)

    @Column(nullable = false)
    private Long amount; // 입출금, 이체 금액

    private Long withdrawAccountBalance; // 출금계좌 현재 잔액
    private Long depositAccountBalance; // 입금계좌 현재 잔액
    // withdrawAccountBalance와 depositAccountBalance은 null일 수 있다.

    // 출금계좌가 null일 경우 Atm기기에서 입금을 했다는 것
    // 입금계좌가 null일 경우 출금
    // 출금계좌아 입금계좌 둘 다 있을 경우 이체
    // 하지만 계속 if문을 사용해서 null을 주는 건 별로 안 좋다.
    // 출금인지 입금인지 구분하기 위해 구분이 입금or출금or이체 정하기 위해 enum에 TransactionEnum 생성

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionEnum gubun; // 입금(ATM으로부터), 출금(ATM으로부터), 이체(다른 계좌로부터)
    // 입금이 될 경우 입금계좌가 있는지 검증
    // 출금할 경우 출금계좌가 있는지 검증 + 출금잔액 검증 + 계좌의 주인인지 검증
    // 0원이 입금됐는지 검증
    // => 이 모든 검증은 service에서 한다.

    @Builder
    public Transaction(Long id, Account withdrawAccount, Account depositAccount, Long amount,
            Long withdrawAccountBalance, Long depositAccountBalance, TransactionEnum gubun) {
        this.id = id;
        this.withdrawAccount = withdrawAccount;
        this.depositAccount = depositAccount;
        this.amount = amount;
        this.withdrawAccountBalance = withdrawAccountBalance;
        this.depositAccountBalance = depositAccountBalance;
        this.gubun = gubun;
    }

}
