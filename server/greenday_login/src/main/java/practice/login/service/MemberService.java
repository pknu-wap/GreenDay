package practice.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import practice.login.domain.Member;
import practice.login.repository.MemberRepository;
import practice.login.domain.dto.CustomOauth2UserDetails;

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
