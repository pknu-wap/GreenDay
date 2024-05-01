package practice.login.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import practice.login.domain.Member;
import practice.login.domain.dto.JoinRequest;
import practice.login.domain.dto.LoginRequest;
import practice.login.service.MemberService;

import java.util.Collection;
import java.util.Iterator;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class OauthLoginController {

    private final MemberService memberService;

    @GetMapping(value = {"", "/"})
    public String loginPage(Model model) {

        model.addAttribute("loginType", "oauth-login");
        model.addAttribute("pageName", "oauth 로그인");

        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @ModelAttribute LoginRequest loginRequest, BindingResult bindingResult) {
        // 만약 바인딩 에러가 있다면 에러 메시지 반환
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("로그인 정보가 올바르지 않습니다.");
        }

        // 로그인 처리 로직 작성 (예: 사용자 인증)

        // 로그인이 성공했을 경우 성공 메시지 반환
        return ResponseEntity.ok("로그인이 성공했습니다.");
    }

}