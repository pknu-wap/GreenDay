package practice.login.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.login.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OauthLoginController {

    private final MemberService memberService;

    @GetMapping("/user-info")
    public ResponseEntity<UserInfoResponse> getUserInfo() {
        String naverEmail = memberService.getNaverEmail();
        String memberName = memberService.getName();

        UserInfoResponse userInfoResponse = new UserInfoResponse(naverEmail, memberName);
        return ResponseEntity.ok(userInfoResponse);
    }
}

class UserInfoResponse {
    private String email;
    private String name;

    public UserInfoResponse(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
