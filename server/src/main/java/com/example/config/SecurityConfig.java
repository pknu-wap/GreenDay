package com.example.config;

import com.example.config.JwtAuthenticationFilter;
import com.example.domain.entity.MemberRole;
import com.nimbusds.oauth2.sdk.auth.JWTAuthentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import java.util.List;

@Configuration
@EnableWebSecurity
@ComponentScan("com.example.config")
public class SecurityConfig{

    private final JwtAuthenticationFilter jwtTokenFilter;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    public SecurityConfig(JwtAuthenticationFilter jwtTokenFilter, AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.jwtTokenFilter = jwtTokenFilter;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().configurationSource(corsConfigurationSource())
                .and()
                // 요청에 대한 인가 규칙 설정
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/oauth-login/admin").hasRole(MemberRole.ADMIN.name())
                        .requestMatchers("/oauth-login/info").authenticated()
                        .requestMatchers("/api/**").permitAll() // API 경로에 대한 설정 추가
                        .requestMatchers("/authuser/**").permitAll() // /authuser 경로에 대한 인증 요구 추가
                        .requestMatchers("/api/private/**").authenticated() // 인증된 사용자만 접근 가능한 리소스
                        .requestMatchers("/write_diary").authenticated() // /write_diary 경로에 대한 인증 요구 추가
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        // OAuth2 로그인 설정
                        .successHandler(authenticationSuccessHandler) // 성공 핸들러 설정
                        .loginPage("/greenday/login")
                        .defaultSuccessUrl("/Home")
                        .failureUrl("/Xlog")
                        .redirectionEndpoint()
                            .baseUri("http://localhost:3000/authuser") // 리다이렉션 엔드포인트 설정
                        .and()
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/oauth-login/logout")
                )
                .csrf().disable()
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        //모든 origin에 대한 요청을 허용하도록 설정함
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // 프론트엔드 서버 주소
        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:3000/authuser"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
