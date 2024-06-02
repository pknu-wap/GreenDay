package com.example.controller;
import com.example.dto.DiaryDto;
import com.example.dto.CustomOauth2UserDetails;
import com.example.service.Diaryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiaryController {
    private final Diaryservice diaryService;

    @Autowired
    public DiaryController(Diaryservice diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/write_diary")
    public ResponseEntity<String> writeDiary(@RequestBody DiaryDto diaryDto, @AuthenticationPrincipal CustomOauth2UserDetails principal) {
        // 소셜 로그인 사용자 정보에서 login_id 설정
        String loginId = principal.getMember().getLoginId(); // 사용자의 아이디를 가져옴
        diaryDto.setLogin_id(loginId);
        diaryService.writeDiary(diaryDto);
        String responseBody = "저장이 완료되었습니다";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
}


