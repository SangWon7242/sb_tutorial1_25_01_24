package com.sbs.tutorial1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// 개발자가 스프링부트 말한다.
// 이 클래스는 웹 요청을 받아서 작업을 한다.
// 해당 클래스는 컨트롤러야!
public class HomeController {
  @GetMapping("/home/main")
  // 개발자가 /home/main 이르는 요청을 보내면 아래 메서드를 실행
  @ResponseBody
  // 아래 메서드를 실행 후 리턴값을 응답으로 삼아
  // body에 출력해줘
  public String showHome() {
    return "어서오세요.";
  }

  @GetMapping("/home/main2")
  @ResponseBody
  public String showHome2() {
    return "환영합니다.";
  }

  @GetMapping("/home/main3")
  @ResponseBody
  public String showHome3() {
    return "스프링부트 획기적이다.";
  }
}
