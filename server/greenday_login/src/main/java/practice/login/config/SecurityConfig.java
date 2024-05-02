package practice.login.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import practice.login.domain.MemberRole;
// 네이버 소셜로그인을 통합하기 위한 spring security 설정 클래스
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // SecurityFilterChain 빈을 정의하여 보안 설정을 구성함
    // 시큐리티 필터 메서드
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // ========= oauth login =========== //
        //접근 권한 설정
        http
                // HTTP 요청에 대한 접근 제어를 구성함
                .authorizeHttpRequests((auth) -> auth
                        // "/oauth-login/admin" 엔드포인트에 대한 접근은 ADMIN 역할을 가진 사용자만 가능함
                        .requestMatchers("/oauth-login/admin").hasRole(MemberRole.ADMIN.name())
                        // "/oauth-login/info" 엔드포인트에 대한 접근은 인증된 사용자만 가능합니다.
                        .requestMatchers("/oauth-login/info").authenticated()
                        // 다른 모든 요청은 인증 없이 허용됨
                        .anyRequest().permitAll()
                );
        http
                // OAuth2 로그인 설정을 구성함
                .oauth2Login((auth) -> auth.loginPage("/oauth-login/login")
                        // 로그인 성공 후 기본적으로 이동할 URL을 지정함
                        .defaultSuccessUrl("/oauth-login")
                        // 로그인 실패 후 이동할 URL을 지정함
                        .failureUrl("/oauth-login/login")
                        // 로그인 페이지에 대한 접근은 인증 없이 허용됨
                        .permitAll());
        // OAuth 2.0 로그인 방식 설정
        // 로그아웃 설정을 구성함
        http
                .logout((auth) -> auth
                        // 로그아웃 URL을 지정함
                        .logoutUrl("/oauth-login/logout"));

        http
                // CSRF 보호를 비활성화함
                .csrf((auth) -> auth.disable());
        // 구성된 SecurityFilterChain을 빌드하고 반환함
        return http.build();
    }
    // BCryptPasswordEncoder 빈을 정의하여 비밀번호를 암호화함
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){

        // BCryptPasswordEncoder의 새 인스턴스를 반환함
        return new BCryptPasswordEncoder();
    }
}
