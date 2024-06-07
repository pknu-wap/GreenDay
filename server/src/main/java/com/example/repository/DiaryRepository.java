package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.domain.entity.DiaryEntity;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
}

