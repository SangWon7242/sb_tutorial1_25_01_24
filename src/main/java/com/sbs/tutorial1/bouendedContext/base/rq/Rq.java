package com.sbs.tutorial1.bouendedContext.base.rq;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Arrays;
import java.util.Enumeration;

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

      if (cookie != null) {
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

    if (value == null) return defaultValue;

    try {
      return Long.parseLong(value);
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  // 쿠키를 가져오는 역할
  private String getCookie(String name, String defaultValue) {
    if (req.getCookies() == null) return defaultValue;

    return Arrays.stream(req.getCookies())
        .filter(cookie -> cookie.getName().equals(name))
        .map(Cookie::getValue)
        .findFirst()
        .orElse(defaultValue);
  }

  public void setSession(String name, long value) {
    HttpSession session = req.getSession();
    session.setAttribute(name, value);

    // System.out.println(getSessionDebugInfo());
  }

  public long getSessionAsLong(String name, long defaultValue) {
    try {
      long value = (long) req.getSession().getAttribute(name);
      return value;

    } catch (Exception e) {
      return defaultValue;
    }
  }

  private String getSessionAsStr(String name, String defaultValue) {
    try {
      String value = (String) req.getSession().getAttribute(name);

      if (value == null) return defaultValue;

      return value;
    } catch (Exception e) {
      return defaultValue;
    }
  }

  public boolean removedSession(String name) {
    HttpSession session = req.getSession();

    // 세션을 가져왔는데 없으면 삭제를 못했다는 의미
    if (session.getAttribute(name) == null) return false;

    session.removeAttribute(name);

    return true;
  }
  
  // 디버깅용 함수
  public String getSessionDebugInfo() {
    HttpSession session = req.getSession();

    String sessionId = session.getId();

    // 세션 ID 출력
    System.out.println("Session ID: " + sessionId);

    // 세션에 저장된 속성(attribute)들 출력
    Enumeration<String> attributeNames = session.getAttributeNames();

    StringBuilder sessionInfo = new StringBuilder("Session ID: " + sessionId + "\nAttributes:\n");
    while (attributeNames.hasMoreElements()) {
      String attributeName = attributeNames.nextElement();
      Object attributeValue = session.getAttribute(attributeName);
      sessionInfo.append(attributeName).append(":").append(attributeValue).append("\n");
    }

    // 세션 생성 시간 확인
    System.out.println("Creation Time: " + session.getCreationTime());

    // 세션 마지막 접근 시간 확인
    System.out.println("Last Accessed Time: " + session.getLastAccessedTime());

    // 세션 유효 시간 확인
    System.out.println("Max Inactive Interval: " + session.getMaxInactiveInterval() + " seconds");

    return sessionInfo.toString();
  }

  public boolean isLogined() {
    long loginedMemberId = getSessionAsLong("loginedMemberId", 0);

    return loginedMemberId > 0;
  }

  public boolean isLogout() {
    return !isLogined();
  }

  public long getLoginedMember() {
    return getSessionAsLong("loginedMemberId", 0);
  }
}
