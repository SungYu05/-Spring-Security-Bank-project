package shop.mtcoding.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import shop.mtcoding.bank.config.enums.UserEnum;
import shop.mtcoding.bank.handler.LoginHandler;

// SecurityConfigChain
// 아파치 톰캣이 만들어둔 것인데 14개 정도를 다 타고 난 뒤 디스패치가 들어온다.
// 이것을 푸는 작업을 할 것
@Configuration
public class SecurityConfig {

    @Autowired
    private LoginHandler loginHandler;
    // configration 파일에서는 생성자 주입(final) 을 절대 쓰지 않기
    // @Autowired 사용하기!! 외우자!

    // 스프링시큐리티는 hash로 하지 않으면 로그인이 안 된다. 다 막힘
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 시큐리티가 들고 있는 hash값을 들고 오는 것
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // SecurityFilterChain 기본적으로 등록된 걸 재등록하기 위해서 @Configuration이랑 @Bean 사용
        http.headers().frameOptions().disable(); // 네거티브 정책: 모든 걸 닫고, 필요한 것만 연다
        // 포지티브 정책이란 모든 것이 열려 있는데 내가 하나하나 잡는 것이다.
        http.csrf().disable();

        http.authorizeHttpRequests()
                .antMatchers("/api/transaction/**").authenticated() // 로그인 안 하면 못 들어오고
                .antMatchers("/api/user/**").authenticated() // 로그인 안 하면 못 들어오고
                .antMatchers("/api/account/**").authenticated() // 로그인 안 하면 못 들어오고
                .antMatchers("/api/admin/**").hasAnyRole("ROLE_" + UserEnum.ADMIN) // 권한이 ADMIN 이어야 들어올 수 있다
                // "ROLE_" 프리픽스 필요 // 묵시적 형변환
                .anyRequest().permitAll()
                .and()
                .formLogin() // localhost:8080/login을 시큐리티가 쓰고 있는데 이제 그걸 안 쓰기 위해 사용
                // formLogin의 디폴트는 x-www-from-urlencoded다.
                // body는 'POST'
                // username과 password를 날릴 경우 x-www-from-urlencoded로 날린다.
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/api/login")
                .defaultSuccessUrl("/") // login 성공시 갈 창
                .failureForwardUrl("/error") // login 실패시 갈 창
                .successHandler(loginHandler) // 잘 될 경우
                .failureHandler(loginHandler); // 실패할 경우

        return http.build();
        // 내 사이트에서 다른 사이트로 아이프레임할 경우
    }

    // 설정을 걸고 나면
    // 로그인을 할 경우 /api/login을 하고 스프링시큐리티 창으로 가게 된다.
    // 원래는 스프링시큐리티 사용하는 경우 어떤 페이지를 가도 login 창으로 갔었다.
    // 이건 다 막은 상태에서 우리가 다시 풀었다는 뜻

}
