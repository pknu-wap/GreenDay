package com.example.service;

import com.example.domain.entity.DiaryEntity;
import com.example.dto.DiaryDto;
import com.example.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public Page<DiaryDto> getDiaryList(int page, int size) {
        // 요청에 따라 페이지당 2개의 일기를 가져오도록 수정
        Pageable pageable = PageRequest.of(page, size * 2, Sort.by("diary_id").ascending());
        Page<DiaryEntity> diaryEntities = historyRepository.findAll(pageable);

        // 일기 엔티티를 일기 DTO로 변환하여 반환
        return diaryEntities.map(diaryEntity -> new DiaryDto(diaryEntity.getDiary_id(), diaryEntity.getDiary_content(), diaryEntity.getLogin_id()));
    }
}

