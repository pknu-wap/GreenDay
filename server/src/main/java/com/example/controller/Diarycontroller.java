package com.example.controller;
import com.example.dto.DiaryDto;
import com.example.service.Diaryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diary")
public class Diarycontroller {
    private final Diaryservice diaryService;

    @Autowired
    public Diarycontroller(Diaryservice diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/write_diary")
    public ResponseEntity<String> writeDiary(@RequestBody DiaryDto diaryDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            String login_id = oAuth2User.getAttribute("id"); // OAuth ID 필드 확인 필요

            // 로그인 ID 설정
            diaryDto.setLoginId(login_id);
            diaryService.writeDiary(diaryDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("저장이 완료되었습니다");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다");
        }
    }
}

