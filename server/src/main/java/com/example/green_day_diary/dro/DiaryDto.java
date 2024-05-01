package com.example.green_day_diary.dro;
import com.example.green_day_diary.domain.entity.DiaryEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class DiaryDto {
    private Long diary_id;
    private String diary_content;
    private LocalDate regdate;
    private String user_email;

    @Builder // 일기 DTO의 빌더 메서드
    public DiaryDto(Long diary_id,String diary_content, LocalDate regdate, String user_email) {
        this.diary_id = diary_id;
        this.diary_content = diary_content;
        this.regdate = regdate;
        this.user_email = user_email;
    }

    public DiaryEntity toEntity(){  // DTO를 엔티티로 변환하는 메서드
        return DiaryEntity.builder()
                .diary_content(diary_content)
                .regdate(regdate)
                .diary_id(diary_id)
                .user_email(user_email)
                .build();
    }
}
