package com.sbs.tutorial1.bouendedContext.base.rq;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Arrays;

@Component
@RequestScope // 이 객체는 매 요청마다 생성된다.
@AllArgsConstructor
public class Rq {
  private final HttpServletRequest req;
  private final HttpServletResponse resp;
  
  public void setCookie(String name, long value) {
    setCookie(name, value + "");
  }

  // 쿠키 저장
  private void setCookie(String name, String value) {
    resp.addCookie(new Cookie(name, value));
  }
  
  // 쿠키 삭제
  public boolean removedCookie(String name) {
    if (req.getCookies() != null) {
      Cookie cookie = Arrays.stream(req.getCookies())
          .filter(c -> c.getName().equals(name))
          .findFirst()
          .orElse(null);

      if(cookie != null) {
        cookie.setMaxAge(0); // 쿠키값을 만료
        resp.addCookie(cookie); // 만료시킨 쿠키를 다시 추가

        return true;
      }
    }

    return false;
  }
  
  // 가져온 쿠키값을 long 타입으로 변환  
  public long getCookieAsLong(String name, long defaultValue) {
    String value = getCookie(name, null);

    if(value == null) return defaultValue;

    try {
      return Long.parseLong(value);
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  // 쿠키를 가져오는 역할
  private String getCookie(String name, String defaultValue) {
    if(req.getCookies() == null) return defaultValue;

    return Arrays.stream(req.getCookies())
        .filter(cookie -> cookie.getName().equals(name))
        .map(Cookie::getValue)
        .findFirst()
        .orElse(defaultValue);
  }
}
