package com.example.service;

import com.example.domain.entity.Member;
import com.example.dto.CustomOauth2UserDetails;
import com.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public String getNaverEmail() {
        Member member = getLoggedInMember();
        return member != null ? member.getEmail() : null;
    }

    public String getName() {
        Member member = getLoggedInMember();
        return member != null ? member.getName() : null;
    }

    public Member getLoginMemberByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    private Member getLoggedInMember() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomOauth2UserDetails) {
            CustomOauth2UserDetails userDetails = (CustomOauth2UserDetails) principal;
            return userDetails.getMember();
        }
        return null;
    }

}
