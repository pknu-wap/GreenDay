package com.example.greendaydiary.domain.entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

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

    @Column(columnDefinition = "date", nullable = false)
    private LocalDate regdate; // 일기 작성일을 저장하는 컬럼

    @Column(columnDefinition = "varchar(20)", nullable = true)
    private String user_email;// 일기 작성자의 이메일을 저장

    @Builder// 롬복을 사용하여 빌더 패턴을 구현하는 어노테이션
    public DiaryEntity(Long diary_id, String diary_content, LocalDate regdate, String user_email) {
        this.diary_id = diary_id; //일기 고유 식별자 초기화
        this.diary_content = diary_content;// 일기 내용 초기화
        this.regdate = regdate;// 일기 작성일 초기화
        this.user_email = user_email;// 일기 작성자 이메일 초기화
    }
}
