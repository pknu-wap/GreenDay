package com.example.controller;

import com.example.dto.DiaryDto;
import com.example.service.HistoryService;
import com.example.config.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public HistoryController(HistoryService historyService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.historyService = historyService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @GetMapping("/diaries")
    public ResponseEntity<?> getDiaries(@RequestHeader("Authorization") String authorizationHeader,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "2") int size) {
        // JWT 토큰을 요청 헤더에서 추출
        String jwt = extractJwtFromHeader(authorizationHeader);

        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "JWT 토큰이 없습니다"));
        }

        // 토큰 검증 및 사용자 정보 추출
        if (!jwtAuthenticationFilter.validateToken(jwt)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "유효하지 않은 JWT 토큰입니다"));
        }

        String loginId = jwtAuthenticationFilter.getUserIdFromJwt(jwt);

        if (loginId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "유효하지 않은 JWT 토큰입니다"));
        }

        // 페이징된 다이어리 목록을 조회
        Page<DiaryDto> diaryPage = historyService.getDiaryList(page, size);

        return new ResponseEntity<>(diaryPage, HttpStatus.OK);
    }

    // 요청 헤더에서 JWT 토큰을 추출하는 메서드
    private String extractJwtFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // "Bearer " 이후의 토큰 부분만 추출
        }
        return null;
    }
}