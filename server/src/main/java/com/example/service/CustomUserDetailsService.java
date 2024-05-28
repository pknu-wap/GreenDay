package com.example.service;

import com.example.domain.entity.Member;
import com.example.dto.CustomSecurityUserDetails;
import com.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    // UserDetailsService 인터페이스의 메서드를 구현하여 사용자 정보를 가져오는 메서드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 사용자의 로그인 ID(username)을 이용하여 회원 정보를 데이터베이스에서 조회함
        Member member = memberRepository.findByLoginId(username);
        // 조회된 회원 정보를 이용하여 CustomSecurityUserDetails 객체를 생성하여 반환함
        if (member != null) {
            return new CustomSecurityUserDetails(member);
        }
        return null;
    }
}
