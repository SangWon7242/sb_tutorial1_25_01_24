package com.sbs.tutorial1.bouendedContext.member.controller;

import com.sbs.tutorial1.base.rq.Rq;
import com.sbs.tutorial1.base.rsData.RsData;
import com.sbs.tutorial1.bouendedContext.member.entity.Member;
import com.sbs.tutorial1.bouendedContext.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;
  private final Rq rq;

  @GetMapping("/login")
  public String showLogin() {
    return "usr/member/login";
  }

  @PostMapping("/login")
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
      rq.setSession("loginedMemberId", member.getId());
    }

    return rsData;
  }

  @GetMapping("/logout")
  @ResponseBody
  public RsData logout() {
    if(rq.isLogout()) {
      return RsData.of("F-1", "이미 로그아웃 상태입니다.");
    }

    rq.removedSession("loginedMemberId");

    return RsData.of("S-1", "로그아웃 되었습니다.");
  }

  @GetMapping("/me")
  public String showMe(Model model) {
    long loginedMemberId = rq.getLoginedMember();

    Member member = memberService.findById(loginedMemberId);

    model.addAttribute("member", member); // view에 데이터 전달

    return "usr/member/me";
  }


  @GetMapping("/session")
  @ResponseBody
  public String showSession() {
    return rq.getSessionDebugInfo().replaceAll("\n", "<br>");
  }
}
