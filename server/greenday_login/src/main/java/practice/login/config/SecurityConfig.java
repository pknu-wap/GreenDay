package practice.login.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import practice.login.domain.MemberRole;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // 시큐리티 필터 메서드
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // ========= oauth login =========== //
        //접근 권한 설정
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/oauth-login/admin").hasRole(MemberRole.ADMIN.name())
                        .requestMatchers("/oauth-login/info").authenticated()
                        .anyRequest().permitAll()
                );
        http
                .oauth2Login((auth) -> auth.loginPage("/oauth-login/login")
                        .defaultSuccessUrl("/oauth-login")
                        .failureUrl("/oauth-login/login")
                        .permitAll());
        // OAuth 2.0 로그인 방식 설정
        http
                .logout((auth) -> auth
                        .logoutUrl("/oauth-login/logout"));

        http
                .csrf((auth) -> auth.disable());

        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){


        return new BCryptPasswordEncoder();
    }
}
