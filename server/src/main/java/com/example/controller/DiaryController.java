package com.example.controller;

import com.example.dto.DiaryDto;
import com.example.service.Diaryservice;
import com.example.config.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class DiaryController {
    private final Diaryservice diaryService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public DiaryController(Diaryservice diaryService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.diaryService = diaryService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @PostMapping("/write_diary")
    public ResponseEntity<String> writeDiary(@RequestBody DiaryDto diaryDto, @RequestHeader("Authorization") String authorizationHeader) {
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

        // 다이어리 DTO에 로그인 ID 설정
        diaryDto.setLogin_id(loginId);
        diaryService.writeDiary(diaryDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("저장이 완료되었습니다");
    }

    // 요청 헤더에서 JWT 토큰을 추출하는 메서드
    private String extractJwtFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // "Bearer " 이후의 토큰 부분만 추출
        }
        return null;
    }
}
