package com.example.config;

import com.example.domain.entity.MemberRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().configurationSource(corsConfigurationSource())
                .and()
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/oauth-login/admin").hasRole(MemberRole.ADMIN.name())
                        .requestMatchers("/oauth-login/info").authenticated()
                        .requestMatchers("/api/**").permitAll() // API 경로에 대한 설정 추가
                        .requestMatchers("/authuser/**").permitAll() // /authuser 경로에 대한 인증 요구 추가
                        .requestMatchers("/write_diary").authenticated() // /write_diary 경로에 대한 인증 요구 추가
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
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
                .csrf().disable();
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
