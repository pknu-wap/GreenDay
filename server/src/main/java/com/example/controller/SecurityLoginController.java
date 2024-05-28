package com.example.controller;

import com.example.dto.LoginRequest;
import com.example.domain.entity.Member;
import com.example.dto.CustomOauth2UserDetails;
import com.example.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greenday")
public class SecurityLoginController {

    private final MemberService memberService;

    public SecurityLoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 홈 페이지를 반환하는 핸들러 메서드
    @GetMapping(value = {"", "/"})
    public ResponseEntity<Member> home() {
        // 현재 인증된 사용자 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // OAuth2 로그인 사용자인 경우
        if (authentication.getPrincipal() instanceof CustomOauth2UserDetails) {
            CustomOauth2UserDetails userDetails = (CustomOauth2UserDetails) authentication.getPrincipal();
            Member loginMember = userDetails.getMember();
            return ResponseEntity.ok(loginMember);
        }
        // 일반 로그인 사용자인 경우
        else if (authentication.getPrincipal() instanceof Member) {
            Member loginMember = (Member) authentication.getPrincipal();
            return ResponseEntity.ok(loginMember);
        }
        // 인증되지 않은 사용자인 경우
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 로그인 요청을 처리하는 핸들러 메서드
    @PostMapping("/write")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // OAuth 로그인 처리 로직 추가
        return ResponseEntity.status(HttpStatus.OK).body("OAuth 로그인이 완료되었습니다");
    }

    // 로그인 페이지를 반환하는 핸들러 메서드
    @GetMapping("/login")
    public ResponseEntity<String> loginPage() {
        // 로그인 페이지 URL을 JSON 형태로 반환
        return ResponseEntity.ok("login");
    }

    // 소셜 로그인 성공 핸들러 메서드
    @PostMapping("/oauth-success")
    public ResponseEntity<String> oauthLoginSuccess() {
        // 성공 메시지를 생성함
        String message = "네이버 소셜로그인이 성공하였습니다.";

        return ResponseEntity.ok(message); // 성공 메시지를 반환함
    }
}
