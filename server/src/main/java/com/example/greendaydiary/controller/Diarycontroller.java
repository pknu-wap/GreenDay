package com.example.greendaydiary.controller;
import com.example.greendaydiary.dto.DiaryDto;
import com.example.greendaydiary.service.Diaryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
// POST 요청을 처리하는 메서드
public class Diarycontroller {
    private final Diaryservice diaryservice;
    @Autowired
    public Diarycontroller(Diaryservice diaryservice){
        // 서비스를 통해 일기를 작성하고 데이터베이스에 저장
        this.diaryservice = diaryservice;
    }
    @PostMapping("/wirte")//api 경로 지정
    public ResponseEntity<String> writeDiary(@RequestBody DiaryDto diaryDto){
        // 작성된 일기에 대한 응답 반환
        diaryservice.write(diaryDto);
        String responseBody = "저장이 완료되었습니다";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

}
