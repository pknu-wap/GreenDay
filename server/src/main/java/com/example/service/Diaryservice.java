package com.example.service;

import com.example.domain.entity.DiaryEntity;
import com.example.dto.DiaryDto;
import com.example.repository.Diaryrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class Diaryservice {
    private final Diaryrepository diaryrepository;//변수에서 할당된 값이 변경될 수 없음을 나타냄

    // 의존성 주입을 통해 Diaryrepository 객체를 주입받음
    @Autowired
    public Diaryservice(Diaryrepository diaryrepository){

        this.diaryrepository =diaryrepository;
    }
    // 클라이언트로부터 받은 일기를 저장하는 메서드
    public void writeDiary(DiaryDto diaryDto){
        DiaryEntity diaryEntity = diaryDto.toEntity();

        //일기 엔티티를 데이터베이스에 저장
        diaryrepository.save(diaryEntity);
    }
    // 페이징하여 일기 목록을 반환하는 메서드
    public Page<DiaryDto> getDiaryList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("diary_id").ascending());
        Page<DiaryEntity> diaryEntities = diaryrepository.findAll(pageable);

        // DiaryEntity를 DiaryDto로 변환하여 새로운 Page 객체 생성
        return diaryEntities.map(diaryEntity -> new DiaryDto(diaryEntity.getDiary_id(), diaryEntity.getDiary_content(), diaryEntity.getLogin_id()));
    }
}
