package com.example.dto;

import com.example.domain.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter //필요한 setter 메소드를 자동으로 생성
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String userEmail;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BoardEntity toEntity() {
        BoardEntity boardEntity = BoardEntity.builder()
                .id(id) // 여기서 DTO의 id 필드가 Entity의 id 필드에 매핑됨
                .userEmail(userEmail)
                .content(content)
                .build();
        return boardEntity;
    }

    @Builder
    public BoardDto(Long id, String userEmail, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.userEmail = userEmail;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    // BoardEntity를 인수로 받는 생성자 추가
    public BoardDto(BoardEntity entity) {
        this.id= entity.getId();
        this.userEmail = entity.getUserEmail();
        this.content = entity.getContent();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
