package practice.login.domain.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import practice.login.domain.Member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOauth2UserDetails implements UserDetails, OAuth2User {

    private final Member member;
    private Map<String, Object> attributes;

    // 생성자
    public CustomOauth2UserDetails(Member member, Map<String, Object> attributes) {

        this.member = member;
        this.attributes = attributes;
    }

    // OAuth2 사용자의 속성을 반환함
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return null;
    }

    // 사용자의 권한을 반환함
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole().name();
            }
        });

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