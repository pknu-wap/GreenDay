package com.example.service;

import com.example.domain.entity.Member;
import com.example.domain.entity.MemberRole;
import com.example.dto.CustomOauth2UserDetails;
import com.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public String getNaverEmail(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String userInfoEndpoint = "https://openapi.naver.com/v1/nid/me";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(userInfoEndpoint, HttpMethod.GET, entity, Map.class);

        Map<String, Object> responseBody = response.getBody();
        Map<String, String> responseData = (Map<String, String>) responseBody.get("response");

        return responseData.get("email");
    }

    // 이메일로 회원을 찾는 메서드 추가
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public String getName(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String userInfoEndpoint = "https://openapi.naver.com/v1/nid/me";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(userInfoEndpoint, HttpMethod.GET, entity, Map.class);

        Map<String, Object> responseBody = response.getBody();
        Map<String, String> responseData = (Map<String, String>) responseBody.get("response");

        return responseData.get("name");
    }


    public synchronized Member saveMember(String loginId, String email, String name, String provider, String providerId) {
        return saveMemberInternal(loginId, email, name, provider, providerId);
    }

    private Member saveMemberInternal(String loginId, String email, String name, String provider, String providerId) {
        // 이미 존재하는 회원인지 확인
        if (memberRepository.existsByLoginId(loginId)) {
            return memberRepository.findByLoginId(loginId);
        }

        synchronized (this) {
            // 동기화 블록 내에서 다시 확인
            if (memberRepository.existsByLoginId(loginId)) {
                return memberRepository.findByLoginId(loginId);
            }

            Member member = Member.builder()
                    .loginId(loginId)
                    .email(email)
                    .name(name)
                    .provider(provider)
                    .providerId(providerId)
                    .role(MemberRole.USER) // 적절한 기본 역할 설정
                    .build();

            System.out.println("Saving member: " + member); // 로그 추가

            Member savedMember = memberRepository.save(member);

            System.out.println("Member saved: " + savedMember); // 로그 추가

            return savedMember;
        }

    }
}
