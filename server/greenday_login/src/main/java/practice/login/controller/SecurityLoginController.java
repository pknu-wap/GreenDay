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
@RequestMapping("/greenday")
public class SecurityLoginController {

    private final MemberService memberService;

    public SecurityLoginController(MemberService memberService) {
        this.memberService = memberService;
    }
    // 홈 페이지를 반환하는 핸들러 메서드
    @GetMapping(value = {"", "/"})
    public String home(Model model) {
        // 현재 로그인한 사용자의 ID를 가져옴
        String loginId = SecurityContextHolder.getContext().getAuthentication().getName();
        // 현재 인증 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 사용자의 권한을 가져옴
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();
        // 로그인한 사용자 정보를 가져옴
        Member loginMember = memberService.getLoginMemberByLoginId(loginId);
        // 로그인한 사용자가 있으면 모델에 이름을 추가함
        if (loginMember != null) {
            model.addAttribute("name", loginMember.getName());
            model.addAttribute("email", loginMember.getEmail());
        }
        // home.html 템플릿을 반환 (임시)
        return "home";
    }
    // 로그인 요청을 처리하는 핸들러 메서드
    @PostMapping("/write")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // OAuth 로그인 처리 로직 추가
        return ResponseEntity.status(HttpStatus.OK).body("OAuth 로그인이 완료되었습니다");
    }
    // 로그인 페이지를 반환하는 핸들러 메서드
    @GetMapping("/login")
    public String loginPage(Model model) {

        // 모델에 소셜 로그인을 나타내는 속성들을 추가함
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "스프링 시큐리티 로그인");
        // 로그인 요청 객체를 생성하여 모델에 추가함
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }
    // 소셜 로그인 성공 핸들러 메서드
    @PostMapping("/oauth-success")
    public ResponseEntity<String> oauthLoginSuccess() {
        // 성공 메시지를 생성함
        String message = "네이버 소셜로그인이 성공하였습니다.";

        return ResponseEntity.ok(message); // 성공 메시지를 반환함
    }
//        @PostMapping("/login")
//        public ResponseEntity<String> loginSuccess(@Valid @ModelAttribute LoginRequest loginRequest, BindingResult bindingResult) {
//            // 바인딩 에러가 있다면 에러 메시지 반환
//            if (bindingResult.hasErrors()) {
//                return ResponseEntity.badRequest().body("로그인 정보가 올바르지 않습니다.");
//            }
//
//            // 로그인 처리 로직 작성
//
//            // 로그인이 성공했을 경우 성공 메시지 반환
//            return ResponseEntity.ok("로그인이 성공했습니다.");
//            return ResponseEntity.ok(message); // 성공 메시지를 반환함
//        }
//    }

}
