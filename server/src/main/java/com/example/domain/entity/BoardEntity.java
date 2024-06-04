package com.example.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "notice_board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 필드 이름 수정

    @Column(length = 50, nullable = false)
    private String userEmail;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Builder
    public BoardEntity(Long boardId, String userEmail, String content) { // 매개변수 이름 수정
        this.id = boardId;
        this.userEmail = userEmail;
        this.content = content;
        // 오류는 안나지만 주의해야 될 부분
        this.createdDate = LocalDateTime.now(); // 직접 설정하고 있으므로,
        this.modifiedDate = LocalDateTime.now(); //Auditing 기능의 자동화와 충돌할 수 있으므로, 해당 필드들의 직접 설정은 제거하는 것이 좋음
    }
}
