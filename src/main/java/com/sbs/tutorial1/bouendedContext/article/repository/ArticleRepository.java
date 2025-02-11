package com.sbs.tutorial1.bouendedContext.article.repository;

import com.sbs.tutorial1.bouendedContext.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// @Repository -> 생략 가능
public interface ArticleRepository extends JpaRepository<Article, Long> {
  // ID를 기준으로 역순 정렬한 데이터 조회
  List<Article> findAllByOrderByIdDesc();
}
