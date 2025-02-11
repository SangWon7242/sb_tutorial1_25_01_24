package com.sbs.tutorial1.bouendedContext.article.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class) // Auditing 활성화
@ToString
// AuditingEntityListener : INSERT, UPDATE 감지하여 필드 값을 재설정
public class Article {
  @Id // primary key
  @GeneratedValue(strategy = IDENTITY) // AUTO_INCREMENT
  private long id;
  
  @CreatedDate
  @Column(updatable = false) // 업데이트 시 수정되지 않도록 설정
  private LocalDateTime createDate; // 데이터 생성날짜
  
  @LastModifiedDate // 마지막 수정 시점 자동 갱신
  private LocalDateTime modifyDate; // 데이터 수정날짜

  private String subject;
  private String content;
}
