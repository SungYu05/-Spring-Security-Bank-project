package shop.mtcoding.bank.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.bank.config.enums.UserEnum;
import shop.mtcoding.bank.domain.AudingTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
// 빈생성자를 내가 NEW 할 일이 없다.
// JPA의 하이버네이트가 GETTER 때리면서 NEW를 때리는 것이기 때문에 사용
// 내가 NEW 하지 않기 위해서 access = AccessLevel.PRIVATE 를 하면 하이버네이트를 제외하고는
// 아무도 new 할 수 없다.
@Getter
@Table(name = "users")
// DB에 users로 만들어야 해서 name에 users가 들어간다.
// 그리고 대소문자 구분하니까 User 이런 식으로 만들지 않기
// 나는 user를 쓰고 싶은데 table은 user를 못 만들기 때문에 users 사용
@Entity
public class User extends AudingTime {

    @Id // p.k 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 오토 인크리먼트 사용을 위해 만든다.
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 20)
    private String email;

    @Enumerated(EnumType.STRING) // enum 쓸 때 사용하는 어노테이션
    @Column(unique = true, nullable = false)
    private UserEnum role; // ADMIN, CUSTOMER
    // 유저의 권한을 주기 위해 enum을 준다.
    // UserEnum 이 없기 때문에 config에 생성

    @Builder
    public User(Long id, String username, String password, String email, UserEnum role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

}
