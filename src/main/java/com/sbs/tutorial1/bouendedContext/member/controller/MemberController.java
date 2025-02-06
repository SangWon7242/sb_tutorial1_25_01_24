package com.sbs.tutorial1.bouendedContext.member.controller;

import com.sbs.tutorial1.bouendedContext.base.rq.Rq;
import com.sbs.tutorial1.bouendedContext.base.rsData.RsData;
import com.sbs.tutorial1.bouendedContext.member.entity.Member;
import com.sbs.tutorial1.bouendedContext.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
@AllArgsConstructor
public class MemberController {
  private final MemberService memberService;
  private final Rq rq;

  @GetMapping("/login")
  @ResponseBody
  public RsData login(String username, String password) {
    if (username == null) {
      return RsData.of("F-1", "로그인 아이디를 입력해주세요.");
    }

    if (password == null) {
      return RsData.of("F-2", "로그인 비번을 입력해주세요.");
    }

    RsData rsData = memberService.tryLogin(username, password);

    if (rsData.isSuccess()) {
      Member member = (Member) rsData.getData();
      rq.setCookie("loginedMemberId", member.getId());
    }

    return rsData;
  }

  @GetMapping("/logout")
  @ResponseBody
  public RsData logout() {
    boolean cookieRemoved = rq.removedCookie("loginedMemberId");

    if(!cookieRemoved) {
      return RsData.of("F-1", "이미 로그아웃 상태입니다.");
    }

    return RsData.of("S-1", "로그아웃 되었습니다.");
  }

  @GetMapping("/me")
  @ResponseBody
  public RsData showMe() {
    long loginedMemberId = rq.getCookieAsLong("loginedMemberId", 0);

    boolean isLogined = loginedMemberId > 0;

    if (!isLogined) {
      return RsData.of("F-1", "로그인 후 이용해주세요.");
    }

    Member member = memberService.findById(loginedMemberId);

    return RsData.of("S-1", "당신의 username(은)는 %s 입니다.".formatted(member.getUsername()));
  }
}
