package com.example.controller;

import com.example.dto.DiaryDto;
import com.example.service.Diaryservice;
import com.example.config.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> writeDiary(@RequestBody DiaryDto diaryDto, @RequestHeader("Authorization") String authorizationHeader) {
        String loginId = jwtAuthenticationFilter.getUserIdFromJwt(authorizationHeader);

        if (loginId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "유효하지 않은 JWT 토큰입니다"));
        }

        // 다이어리 DTO에 로그인 ID 설정
        diaryDto.setLogin_id(loginId);
        diaryService.writeDiary(diaryDto);

        // 성공 응답 생성
        Map<String, String> response = new HashMap<>();
        response.put("message", "저장이 완료되었습니다");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 일기 목록을 페이징하여 반환하는 API 엔드포인트
    @GetMapping("/history")
    public ResponseEntity<Page<DiaryDto>> getDiaryList(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestHeader("Authorization") String authorizationHeader) {

        String loginId = jwtAuthenticationFilter.getUserIdFromJwt(authorizationHeader);

        if (loginId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Page<DiaryDto> diaryList = diaryService.getDiaryList(page, 2);

        if (diaryList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        return ResponseEntity.ok(diaryList);
    }
}
