package com.example.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class LogoutController {

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        // 세션 무효화
        session.invalidate();

        // 쿠키 삭제
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0); // 쿠키 만료 시간을 0으로 설정하여 무효화
                response.addCookie(cookie); // 응답 헤더에 추가하여 클라이언트에게 전송
            }
        }

        // 로그아웃 성공 시 메시지 반환
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "로그아웃되었습니다");

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
