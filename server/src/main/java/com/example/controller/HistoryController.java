package com.example.controller;

import com.example.domain.entity.DiaryEntity;
import com.example.service.HistoryService;
import com.example.config.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/get")
public class HistoryController {

    private final HistoryService historyService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public HistoryController(HistoryService historyService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.historyService = historyService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @GetMapping("/diary/history")
    public ResponseEntity<?> getDiaryHistory(@RequestParam("page") int page, @RequestHeader("Authorization") String authorizationHeader) {
        // JWT 토큰을 요청 헤더에서 추출
        String jwt = extractJwtFromHeader(authorizationHeader);

        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT 토큰이 없습니다");
        }

        // 토큰 검증 및 사용자 정보 추출
        if (!jwtAuthenticationFilter.validateToken(jwt)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 JWT 토큰입니다");
        }

        String loginId = jwtAuthenticationFilter.getUserIdFromJwt(jwt);

        if (loginId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 JWT 토큰입니다");
        }

        // 일기 히스토리 가져오기
        List<DiaryEntity> diaryHistory = historyService.getDiaryHistory(page);

        if (diaryHistory.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("더 이상 일기가 없습니다");
        }

        return ResponseEntity.ok(diaryHistory);
    }

    // 요청 헤더에서 JWT 토큰을 추출하는 메서드
    private String extractJwtFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // "Bearer " 이후의 토큰 부분만 추출
        }
        return null;
    }
}
