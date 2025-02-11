package com.sbs.tutorial1.base.initData;

import com.sbs.tutorial1.base.rq.Rq;
import com.sbs.tutorial1.bouendedContext.article.service.ArticleService;
import com.sbs.tutorial1.bouendedContext.member.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration // 컴포넌트와 비슷한 역할
@Profile({"dev", "test"})
// production(운영 환경, 라이브 서버)이 아니다.
// NotProd : 개발환경이 아니고, 테스트 환경이 아닐때만 실행
public class NotProd {
  @Bean
  CommandLineRunner initData(MemberService memberService, ArticleService articleService) {
    return args -> {
      memberService.join("user1", "1234");
      memberService.join("user2", "1234");
      memberService.join("user3", "1234");
      memberService.join("love", "2222");
      memberService.join("test", "123456");
      memberService.join("giving", "23456");
      memberService.join("dance", "3452");
      memberService.join("spring", "14110");
      memberService.join("summer", "8899");
      memberService.join("winter", "331122");

      articleService.write("제목1", "내용1");
      articleService.write("제목2", "내용2");
    };
  }
}
