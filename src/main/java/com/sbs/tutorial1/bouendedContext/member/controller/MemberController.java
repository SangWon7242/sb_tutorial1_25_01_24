package com.sbs.tutorial1.bouendedContext.member.controller;

import com.sbs.tutorial1.bouendedContext.base.rsData.RsData;
import com.sbs.tutorial1.bouendedContext.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
@AllArgsConstructor
public class MemberController {
  
  // 필드 주입
  /*
  @Autowired
  private MemberService memberService; 
  */

  private final MemberService memberService;

  @GetMapping("/login")
  @ResponseBody
  public RsData login(String username, String password) {
    if(username == null) {
      return RsData.of("F-1", "로그인 아이디를 입력해주세요.");
    }

    if(password == null) {
      return RsData.of("F-2", "로그인 비번을 입력해주세요.");
    }

    return memberService.tryLogin(username, password);
  }
}
