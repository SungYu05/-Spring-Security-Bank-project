package shop.mtcoding.bank.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserRepository;

@Service
public class LoginUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // LoginUser loginUser = (LoginUser)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 이게 LoginDto가 된다. return new LoginUser(user); 이걸 풀어서 알려준 것. (주석처리)

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("username을 찾을 수 없습니다."));

        return new LoginUser(user);
        // userDetails를 inplements 한 findById한 user 주입하면 끝난다.
        // LoginUser(user)가 session에 담긴다.
    }

}
