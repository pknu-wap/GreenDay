package com.example.green_day_diary.controller;
import com.example.green_day_diary.dro.DiaryDto;
import com.example.green_day_diary.service.Diaryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Diarycontroller {
    private final Diaryservice diaryservice;
    @Autowired
    public Diarycontroller(Diaryservice diaryservice){
        // 서비스를 통해 일기를 작성하고 데이터베이스에 저장
        this.diaryservice = diaryservice;
    }
    @PostMapping("/wirte_diary")//api 경로 지정
    public ResponseEntity<String> writeDiary(@RequestBody DiaryDto diaryDto){
        // 작성된 일기에 대한 응답 반환
        diaryservice.writeDiary(diaryDto);
        String responseBody = "저장이 완료되었습니다";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
}
