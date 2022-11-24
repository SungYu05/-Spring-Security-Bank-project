package shop.mtcoding.bank.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import shop.mtcoding.bank.domain.user.User;

@Getter
@RequiredArgsConstructor
public class LoginUser implements UserDetails {

    private final User user; // 진짜 User를 넣고 싶기 때문에 커스터 마이징을 한다.

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> "ROLE_" + user.getRole()); // 람다식 사용 // 권한은 하나가 된다.
        // 권한을 여러개 주고 싶을 때는 String을 사용해서 콤마를 쓴다 (쓰진 않기)
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { // 1년 지나면 휴먼 계정이 된다는 내용
        // 모르는 건 일단 true
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 계정 LOCK 걸리는 것
        // 모르는 건 일단 true
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 모르는 건 일단 true
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 모르는 건 일단 true
        return true;
    }

}
