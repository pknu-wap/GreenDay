package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AuthUserController {
    private static final Logger logger = LoggerFactory.getLogger(AuthUserController.class);

    private final RestTemplate restTemplate;


    public AuthUserController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/authuser")
    public ResponseEntity<String> handleAuthUser(@RequestParam String code,@RequestParam String state) {
        // 받아온 code 값을 이용하여 다른 서비스로 인증 처리를 진행
        logger.info("Received /authuser request with code: {} and state: {}", code, state);

        String clientId = "o72MtePRXsbwlztUtJoj";
        String clientSecret = "syAjjCYexm";
        String redirectUri = "http://localhost:3000/authuser";

        String tokenUrl = "https://api.example.com/auth/token";
        String requestBody = "grant_type=authorization_code" +
                "&client_id=" + clientId +
                "&client_secret=" + clientSecret +
                "&code=" + code +
                "&redirect_uri=" + redirectUri +
                "&state=" + state; // state 파라미터 추가

        logger.info("Sending POST request to token URL: {}", tokenUrl);
        logger.info("Request body: {}", requestBody);

        // POST 요청으로 토큰을 요청하고 응답을 받음
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(tokenUrl, requestBody, String.class);
        String responseBody = responseEntity.getBody();

        // 응답을 그대로 클라이언트에게 반환
        return ResponseEntity.ok(responseBody);
    }
}
