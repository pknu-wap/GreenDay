package practice.login.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice.login.domain.Member;
import practice.login.domain.MemberRole;

// 회원가입 요청을 처리하기 위한 DTO(데이터 전송 객체)인 JoinRequest 클래스
@Getter @Setter
@NoArgsConstructor
public class JoinRequest {
    // ID를 입력받는 필드. 비어 있을 수 없음을 나타냄
    @NotBlank(message = "ID를 입력하세요.")
    private String loginId;
    // 이름을 입력받는 필드. 비어 있을 수 없음을 나타냄
    @NotBlank(message = "이름을 입력하세요.")
    private String name;

    // Member 엔티티로 변환하는 메서드
    public Member toEntity(){
        // Member 객체를 빌더 패턴을 통해 생성함
        return Member.builder()
                .loginId(this.loginId) // ID를 설정함
//                .password(this.password)
                .name(this.name) // 이름을 설정함
                .role(MemberRole.USER) // 사용자의 역할을 기본적으로 USER로 설정함
                .build(); // Member 객체를 생성하여 반환함
    }

}
