package com.example.repository;

import com.example.domain.entity.DiaryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Diaryrepository extends JpaRepository<DiaryEntity, Long> {
}
