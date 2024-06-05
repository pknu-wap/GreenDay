package com.example.controller;

import com.example.domain.entity.DiaryEntity;
import com.example.service.HistoryService;
import com.example.config.JwtTokenProvider; // JwtTokenProvider import 추가
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/get")
public class HistoryController {/*

    private final HistoryService historyService;
    private final JwtTokenProvider jwtTokenProvider; // JwtTokenProvider로 변경

    // HistoryController 생성자
    @Autowired
    public HistoryController(HistoryService historyService, JwtTokenProvider jwtTokenProvider) { // JwtTokenProvider로 변경
        this.historyService = historyService;
        this.jwtTokenProvider = jwtTokenProvider; // JwtTokenProvider로 변경
    }

    // 일기 히스토리를 가져오는 API 엔드포인트
    @GetMapping("/history")
    public ResponseEntity<?> getDiaryHistory(@RequestParam("page") int page, @RequestHeader("Authorization") String authorizationHeader) {
        // JWT 토큰을 JwtTokenProvider를 사용하여 추출
        String jwt = jwtTokenProvider.resolveToken(authorizationHeader);

        if (jwt == null) {
            // 토큰이 없을 경우 UNAUTHORIZED 상태 코드 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT 토큰이 없습니다");
        }

        // 토큰 검증 및 사용자 정보 추출
        if (!jwtTokenProvider.validateToken(jwt)) {
            // 토큰이 유효하지 않을 경우 UNAUTHORIZED 상태 코드 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 JWT 토큰입니다");
        }

        String loginId = jwtTokenProvider.getUserIdFromJwt(jwt);

        if (loginId == null) {
            // 토큰에서 사용자 ID를 추출할 수 없을 경우 UNAUTHORIZED 상태 코드 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 JWT 토큰입니다");
        }

        // 일기 히스토리 가져오기
        List<DiaryEntity> diaryHistory = historyService.getDiaryHistory(page);

        if (diaryHistory.isEmpty()) {
            // 일기 히스토리가 비어있을 경우 NO_CONTENT 상태 코드 반환
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("더 이상 일기가 없습니다");
        }

        // 일기 히스토리가 존재할 경우 OK 상태 코드와 함께 일기 목록 반환
        return ResponseEntity.ok(diaryHistory);
    }*/
}
