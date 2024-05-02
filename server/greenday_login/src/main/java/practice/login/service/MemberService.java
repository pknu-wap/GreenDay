package practice.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.login.domain.Member;
import practice.login.domain.dto.JoinRequest;
import practice.login.domain.dto.LoginRequest;
import practice.login.repository.MemberRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 로그인 ID 중복 확인 메서드
    public boolean checkLoginIdDuplicate(String loginId){
        return memberRepository.existsByLoginId(loginId);
    }

    // 회원가입 메서드
    public void join(JoinRequest joinRequest) {
        memberRepository.save(joinRequest.toEntity());
    }

    // Spring Security를 이용한 회원가입 메서드
    public void securityJoin(JoinRequest joinRequest){
        // 이미 존재하는 로그인 ID인 경우 가입 처리하지 않음
        if(memberRepository.existsByLoginId(joinRequest.getLoginId())){
            return;
        }
        // 회원 정보 저장
        memberRepository.save(joinRequest.toEntity());
    }
    // 로그인 메서드
    public Member login(LoginRequest loginRequest) {
        // 주어진 로그인 ID로 회원을 조회함
        Member findMember = memberRepository.findByLoginId(loginRequest.getLoginId());

        // 회원이 존재하지 않으면 null을 반환함
        if(findMember == null){
            return null;
        }

        return findMember;
    }

    // ID를 이용한 회원 조회 메서드
    public Member getLoginMemberById(Long memberId){
        // 회원 ID가 null이면 null을 반환함
        if(memberId == null) return null;

        // 주어진 회원 ID로 회원을 조회함
        Optional<Member> findMember = memberRepository.findById(memberId);
        return findMember.orElse(null);

    }

    // 로그인 ID를 이용한 회원 조회 메서드
    public Member getLoginMemberByLoginId(String loginId){
        // 로그인 ID가 null이면 null을 반환함
        if(loginId == null) return null;

        // 주어진 로그인 ID로 회원을 조회함
        return memberRepository.findByLoginId(loginId);

    }

}
