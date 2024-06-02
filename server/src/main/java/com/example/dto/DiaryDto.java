package com.example.dto;

import com.example.domain.entity.DiaryEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DiaryDto {
    private Long diary_id;
    private String diary_content;
    private LocalDate regdate;
    private String login_id;

    @Builder
    public DiaryDto(Long diary_id, String diary_content, LocalDate regdate, String login_id) {
        this.diary_id = diary_id;
        this.diary_content = diary_content;
        this.regdate = regdate;
        this.login_id = login_id;
    }

    public DiaryEntity toEntity() {
        return DiaryEntity.builder()
                .diary_content(diary_content)
                .regdate(regdate)
                .diary_id(diary_id)
                .login_id(login_id)
                .build();
    }
}
