package com.example.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member", uniqueConstraints = {
        @UniqueConstraint(columnNames = "login_Id"),
        @UniqueConstraint(columnNames = "email")
})
public class Member {
    // 회원 식별자를 나타내는 필드
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;
    // 회원의 로그인 ID를 나타내는 필드
    private String loginId;
    // 회원의 이름을 나타내는 필드
    private String name;
    // 회원의 이메일을 나타내는 필드
    private String email;
    // 회원의 역할(Role)을 나타내는 필드
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    // OAuth2 공급자(Provider)를 나타내는 필드
    // provider : naver가 들어감
    private String provider;

    // OAuth2 공급자에서 제공하는 회원의 고유 ID를 나타내는 필드
    // providerId : 네이버 로그인을 한 유저의 고유 ID가 들어감
    private String providerId;

}
