package com.sbs.tutorial1.bouendedContext.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Article {
  @Id // primary key
  @GeneratedValue(strategy = IDENTITY) // AUTO_INCREMENT
  private long id;
  private LocalDateTime createDate; // 데이터 생성날짜
  private LocalDateTime modifyDate; // 데이터 수정날짜
  private String subject;
  private String content;
}
