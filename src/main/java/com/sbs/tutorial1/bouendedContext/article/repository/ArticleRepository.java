package com.sbs.tutorial1.bouendedContext.article.repository;

import com.sbs.tutorial1.bouendedContext.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository -> 생략 가능
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
