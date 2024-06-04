package com.example.dto;

import com.example.domain.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter //필요한 setter 메소드를 자동으로 생성
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long boardId; // 필드 이름 수정

    private String userEmail;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public BoardEntity toEntity() {
        BoardEntity boardEntity = BoardEntity.builder()
                .boardId(boardId) // 메서드 이름 수정
                .userEmail(userEmail)
                .content(content)
                .build();
        return boardEntity;
    }

    @Builder
    public BoardDto(Long boardId, String userEmail, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.boardId = boardId;
        this.userEmail = userEmail;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
