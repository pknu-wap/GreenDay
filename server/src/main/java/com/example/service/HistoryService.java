package com.example.service;

import com.example.domain.entity.DiaryEntity;
import com.example.repository.Diaryrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {/*

    private final Diaryrepository diaryrepository;

    @Autowired
    public HistoryService(Diaryrepository diaryrepository) {
        this.diaryrepository = diaryrepository;
    }

    // 페이징 처리하여 일기 히스토리를 가져오는 메서드
    public Page<DiaryEntity> getDiaryHistory(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return diaryrepository.findAll(pageable);
    }*/
}

