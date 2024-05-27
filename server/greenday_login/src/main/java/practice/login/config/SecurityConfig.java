package practice.login.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import practice.login.domain.MemberRole;

import java.util.List;

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
        // CORS 설정
        http.cors(c -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://localhost:3000")); // 클라이언트 출처를 허용
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // 사용 가능한 메서드 지정
                    config.setAllowedHeaders(List.of("*")); // 모든 헤더 허용
                    config.setAllowCredentials(true); // 자격 증명을 허용
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", config);
                    c.configurationSource(source);
                })

                // HTTP 요청에 대한 접근 제어를 구성함
                // HTTP 보안 설정
                .authorizeHttpRequests((auth) -> auth
                        // "/oauth-login/admin" 엔드포인트에 대한 접근은 ADMIN 역할을 가진 사용자만 가능함
                        .requestMatchers("/oauth-login/admin").hasRole(MemberRole.ADMIN.name())
                        // "/oauth-login/info" 엔드포인트에 대한 접근은 인증된 사용자만 가능합니다.
                        .requestMatchers("/oauth-login/info").authenticated()
                        // 다른 모든 요청은 인증 없이 허용됨
                        .anyRequest().permitAll()
                )
                // OAuth2 로그인 설정을 구성함
                .oauth2Login((auth) -> auth.loginPage("/greenday/login")

                        // 로그인 성공 후 기본적으로 이동할 URL을 지정함
                        .defaultSuccessUrl("/greenday")
                        // 로그인 실패 후 이동할 URL을 지정함
                        .failureUrl("/greenday/login")
                        // 로그인 페이지에 대한 접근은 인증 없이 허용됨
                        .permitAll()
                )
        // OAuth 2.0 로그인 방식 설정
        // 로그아웃 설정을 구성함
                .logout((auth) -> auth
                        // 로그아웃 URL을 지정함
                        .logoutUrl("/oauth-login/logout")
                )
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
