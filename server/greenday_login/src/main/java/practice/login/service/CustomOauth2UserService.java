package practice.login.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import practice.login.domain.Member;
import practice.login.domain.MemberRole;
import practice.login.domain.dto.*;
import practice.login.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    // OAuth2 사용자 정보 로드 메서드 오버라이드
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 부모 클래스(DefaultOAuth2UserService)의 loadUser 메서드를 호출하여 OAuth2 사용자 정보를 가져옴
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("getAttributes : {}",oAuth2User.getAttributes());

        // 사용자 정보를 제공하는 OAuth2 공급자(Provider)를 가져옴
        String provider = userRequest.getClientRegistration().getRegistrationId();

        // OAuth2UserInfo 인터페이스를 구현한 클래스 변수 선언
        OAuth2UserInfo oAuth2UserInfo = null;

        // 네이버 소셜 로그인인 경우
       if (provider.equals("naver")) {
            log.info("네이버 로그인");
           // 네이버 사용자 정보를 담은 NaverUserDetails 객체 생성
            oAuth2UserInfo = new NaverUserDetails(oAuth2User.getAttributes());
      }

        // 사용자 정보에서 공급자 제공 ID, 이메일, 로그인 ID, 이름을 가져옴
        String providerId = oAuth2UserInfo.getProviderId();
        String email = oAuth2UserInfo.getEmail();
        String loginId = provider + "_" + providerId;
        String name = oAuth2UserInfo.getName();

        // 로그인 ID를 이용하여 회원 정보 조회
        Member findMember = memberRepository.findByLoginId(loginId);
        Member member;

        // 조회된 회원 정보가 없는 경우 새로운 회원으로 등록
        if (findMember == null) {
            member = Member.builder()
                    .loginId(loginId)
                    .name(name)
                    .provider(provider)
                    .providerId(providerId)
                    .role(MemberRole.USER)
                    .build();
            memberRepository.save(member);
        } else{
            member = findMember;
        }
        // CustomOauth2UserDetails 객체를 생성하여 사용자 정보 반환
        return new CustomOauth2UserDetails(member, oAuth2User.getAttributes());
    }
}
