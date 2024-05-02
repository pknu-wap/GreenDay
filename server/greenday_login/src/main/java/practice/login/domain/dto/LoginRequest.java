package practice.login.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//사용자의 로그인 요청을 처리하기 위해 필요한 정보를 담고 있음
@Getter @Setter
@NoArgsConstructor
public class LoginRequest {
    // 사용자의 로그인 ID를 나타내는 필드
    private String loginId;
    // 사용자의 비밀번호를 나타내는 필드
    private String password;
}
