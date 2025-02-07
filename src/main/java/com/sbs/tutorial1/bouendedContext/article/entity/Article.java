package com.sbs.tutorial1.bouendedContext.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Article {
  @Id // primary key
  private long id;
  private String subject;
  private String content;
}
