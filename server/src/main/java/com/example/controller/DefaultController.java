package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 루트 경로("/")로 들어오는 모든 GET 요청을 처리
// 요청이 들어오면 "Welcome to the application!" 메시지를 응답으로 반환
@RestController
public class DefaultController {

    @GetMapping("/")
    public ResponseEntity<String> handleDefaultRequest() {
        return ResponseEntity.ok("Welcome to the application!");
    }
}

