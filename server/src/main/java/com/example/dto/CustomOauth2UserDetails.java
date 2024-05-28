package com.example.dto;

import com.example.domain.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOauth2UserDetails implements UserDetails, OAuth2User {

    private final Member member;
    private Map<String, Object> attributes;
    private String email; // 추가: 이메일 저장 필드
    private String name;  // 추가: 이름 저장 필드

    // 생성자
    public CustomOauth2UserDetails(Member member, Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
        this.email = member.getEmail(); // 추가: 이메일 초기화
        this.name = member.getName();   // 추가: 이름 초기화
    }

    // Member 객체를 반환하는 메서드 추가
    public Member getMember() {
        return member;
    }

    // 이메일을 반환하는 메서드 추가
    public String getEmail() {
        return email;
    }

    // 이름을 반환하는 메서드 추가
    public String getFullName() {
        return name;
    }

    // OAuth2 사용자의 속성을 반환함
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return member.getName(); // Member 객체의 이름을 반환
    }

    // 사용자의 권한을 반환함
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> member.getRole().name());
        return collection;
    }

    // 사용자의 비밀번호를 반환함. (사용되지 않으므로 null 반환)
    @Override
    public String getPassword() {
        return null;
    }

    // 사용자의 로그인 ID를 반환함
    @Override
    public String getUsername() {
        return member.getLoginId();
    }

    // 사용자 계정이 만료되지 않았는지 여부를 반환함
    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정이 만료되지 않았음을 반환함
    }

    // 사용자 계정이 잠겨있지 않은지 여부를 반환함
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 사용자의 자격 증명이 만료되지 않았는지 여부를 반환함
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 사용자 계정이 활성화되어 있는지 여부를 반환함
    @Override
    public boolean isEnabled() {
        return true;
    }
}
