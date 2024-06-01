package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CustomAuthenticationHandler extends SimpleUrlAuthenticationFailureHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 로그인 성공 시 처리
        //  onAuthenticationSuccess 메서드에서 로그인 성공 시 "http://localhost:3000/Home"으로 리다이렉트
        response.sendRedirect("http://localhost:3000/Home");
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException exception) throws IOException, ServletException {
        // 로그인 실패 시 처리
        //  onAuthenticationFailure 메서드에서 로그인 실패 시 "http://localhost:3000"으로 리다이렉트
        response.sendRedirect("http://localhost:3000");
    }
}

