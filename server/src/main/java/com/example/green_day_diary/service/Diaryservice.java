package com.example.green_day_diary.service;

import com.example.green_day_diary.domain.entity.DiaryEntity;
import com.example.green_day_diary.dro.DiaryDto;
import com.example.green_day_diary.repository.Diaryrepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
