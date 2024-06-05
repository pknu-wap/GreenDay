package com.example.repository;

import com.example.domain.entity.DiaryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Diaryrepository extends JpaRepository<DiaryEntity, Long> {
    Page<DiaryEntity> findAll(Pageable pageable); // 페이징 처리를 위해 Pageable을 사용하여 findAll 메서드를 재정의
}
