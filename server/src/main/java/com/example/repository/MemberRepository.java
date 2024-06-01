package com.example.repository;


import com.example.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring Data JPA Repository 인터페이스 정의
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 주어진 로그인 ID가 이미 존재하는지 확인하는 메서드
    boolean existsByLoginId(String loginId);
    // 주어진 로그인 ID에 해당하는 회원을 찾는 메서드
    Member findByLoginId(String loginId);
    Member findByEmail(String email);

}
