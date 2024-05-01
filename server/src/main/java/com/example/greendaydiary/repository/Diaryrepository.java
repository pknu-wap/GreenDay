package com.example.greendaydiary.repository;
import com.example.greendaydiary.domain.entity.DiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
//인터페이스를 생성 후 JpaRepository를 이용하여 상속하면 기본적인 CRUD 메소드가 자동으로 생성
//@Repository를 추가할 필요가 없음
public interface Diaryrepository extends JpaRepository<DiaryEntity, Long> {
}
//Diaryrepository는 인터페이스이며 Spring Data JPA에서 자동으로 구현해주는 CRUD 메서드를 사용하기 위한 것
