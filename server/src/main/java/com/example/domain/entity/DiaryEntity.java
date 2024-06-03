package com.example.domain.entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="diary")
@Getter
@NoArgsConstructor
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diary_id;// 일기 엔티티의 고유 식별자

    @Column(columnDefinition = "TEXT", nullable = false)
    private String diary_content;// 일기 내용을 저장


    @Column(columnDefinition = "varchar(255)", nullable = true)
    private String login_id;// 일기 작성자의 이메일을 저장

    @Builder// 롬복을 사용하여 빌더 패턴을 구현하는 어노테이션
    public DiaryEntity(Long diary_id, String diary_content, String login_id) {
        this.diary_id = diary_id; //일기 고유 식별자 초기화
        this.diary_content = diary_content;// 일기 내용 초기화
        this.login_id = login_id;// 일기 작성자 이메일 초기화
    }

}
