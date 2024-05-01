package practice.login.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import practice.login.domain.Member;
import practice.login.domain.dto.JoinRequest;
import practice.login.domain.dto.LoginRequest;
import practice.login.service.MemberService;

import java.util.Collection;
import java.util.Iterator;

@Controller
@RequestMapping("/oauth-login")
public class SecurityLoginController {

    private final MemberService memberService;

    public SecurityLoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = {"", "/"})
    public String home(Model model) {

        String loginId = SecurityContextHolder.getContext().getAuthentication().getName();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        Member loginMember = memberService.getLoginMemberByLoginId(loginId);

        if (loginMember != null) {
            model.addAttribute("name", loginMember.getName());
        }

        return "home";
    }

    @PostMapping("/write")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // OAuth 로그인 처리 로직 추가
        return ResponseEntity.status(HttpStatus.OK).body("OAuth 로그인이 완료되었습니다");
    }

    @GetMapping("/login")
    public String loginPage(Model model) {

        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "스프링 시큐리티 로그인");

        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }
}
