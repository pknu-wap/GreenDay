package com.example.service;

import com.example.domain.entity.DiaryEntity;
import com.example.repository.Diaryrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    private final Diaryrepository diaryrepository;

    @Autowired
    public HistoryService(Diaryrepository diaryrepository) {
        this.diaryrepository = diaryrepository;
    }

    public List<DiaryEntity> getDiaryHistory(int page) {
        Pageable pageable = PageRequest.of(page, 2);
        return diaryrepository.findAll(pageable).getContent();
    }
}
