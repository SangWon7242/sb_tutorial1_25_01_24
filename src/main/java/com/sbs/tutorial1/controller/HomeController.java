package com.sbs.tutorial1.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
// 개발자가 스프링부트 말한다.
// 이 클래스는 웹 요청을 받아서 작업을 한다.
// 해당 클래스는 컨트롤러야!
public class HomeController {
  int num;
  List<Person> personList;

  public HomeController() {
    num = -1;
    personList = new ArrayList<>();
  }

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
    return "환영합니다.123123123";
  }

  @GetMapping("/home/main3")
  @ResponseBody
  public String showHome3() {
    return "스프링부트 획기적이다.";
  }

  @GetMapping("/home/increase")
  @ResponseBody
  public int showIncrease() {
    num++;
    return num;
  }

  @GetMapping("/home/plus")
  @ResponseBody
  // @RequestParam 생략 가능
  // @RequestParam(defaultValue = "0") : b라는 이름으로 파라미터를 넘기지 않으면
  // 기본값을 0으로 사용하겠다.
  public int showPlus(@RequestParam int a, @RequestParam(defaultValue = "0") int b) {
    return a + b;
  }

  @GetMapping("/home/returnBoolean")
  @ResponseBody
  public boolean showReturnBoolean() {
    return true;
  }

  @GetMapping("/home/returnDouble")
  @ResponseBody
  public double showReturnDouble() {
    return Math.PI;
  }

  @GetMapping("/home/returnArray")
  @ResponseBody
  public int[] showReturnArray() {
    int[] arr = new int[]{10, 20, 30};
    // System.out.println(arr); // 주소값
    return arr;
  }

  @GetMapping("/home/returnList")
  @ResponseBody
  public List<Integer> showReturnList() {
    List<Integer> list = new ArrayList<>() {{
      add(10);
      add(20);
      add(30);
    }};

    /*
    List<Integer> list = new ArrayList<>();
    list.add(10);
    list.add(20);
    list.add(30);
     */

    return list;
  }

  @GetMapping("/home/returnMap")
  @ResponseBody
  public Map<String, Object> showReturnMap() {
    Map<String, Object> map = new LinkedHashMap<>() {{
      put("id", 1);
      put("subject", "스프링부트는 무엇인가요?");
      put("content", "스프링부트는 무엇이고 어떻게?...");
      put("articleNo", new ArrayList<>() {{
        add(1);
        add(2);
        add(3);
      }});
    }};

    return map;
  }

  @GetMapping("/home/returnArticle")
  @ResponseBody
  public Article showReturnArticle() {
    Article article = new Article(1, "제목1", "내용1", new ArrayList<>() {{
      add(1);
      add(2);
      add(3);
    }});

    return article;
  }

  @GetMapping("/home/returnArticle2")
  @ResponseBody
  public Article2 showReturnArticle2() {
    Article2 article = new Article2(1, "제목1", "내용1", new ArrayList<>() {{
      add(1);
      add(2);
      add(3);
    }});

    return article;
  }

  @GetMapping("/home/returnArticleMapList")
  @ResponseBody
  public List<Map<String, Object>> showReturnMapList() {
    Map<String, Object> articleMap1 = new LinkedHashMap<>() {{
      put("id", 1);
      put("subject", "제목1");
      put("content", "내용1");
      put("articleNo", new ArrayList<>() {{
        add(1);
        add(2);
        add(3);
      }});
    }};

    Map<String, Object> articleMap2 = new LinkedHashMap<>() {{
      put("id", 2);
      put("subject", "제목2");
      put("content", "내용2");
      put("articleNo", new ArrayList<>() {{
        add(4);
        add(5);
        add(6);
      }});
    }};

    List<Map<String, Object>> list = new ArrayList<>();
    list.add(articleMap1);
    list.add(articleMap2);

    return list;
  }

  @GetMapping("/home/returnArticleList")
  @ResponseBody
  public List<Article2> showReturnArticleList() {
    Article2 article1 = new Article2(1, "제목1", "내용1", new ArrayList<>() {{
      add(1);
      add(2);
      add(3);
    }});

    Article2 article2 = new Article2(2, "제목2", "내용2", new ArrayList<>() {{
      add(4);
      add(5);
      add(6);
    }});

    List<Article2> list = new ArrayList<>();
    list.add(article1);
    list.add(article2);

    return list;
  }

  @GetMapping("/home/personTestCase")
  @ResponseBody
  public String personTestCase() {
    personList.add(new Person("홍길동", 11));
    personList.add(new Person("홍길순", 22));
    personList.add(new Person("임꺽정", 33));

    return "테스트케이스 추가";
  }

  @GetMapping("/home/addPerson")
  @ResponseBody
  public String addPerson(String name, int age) {
    Person p = new Person(name, age);
    System.out.println(p);

    personList.add(p);

    return "%d번 사람이 추가되었습니다.".formatted(p.getId());
  }

  @GetMapping("/home/removePerson")
  @ResponseBody
  public String removePerson(int id) {
    /*
    Person target = null;
    for(Person p : personList) {
      if(p.getId() == id) {
        target = p;
        break;
      }
    }
    
    if(target == null) {
      return "%d번 사람은 존재하지 않습니다.".formatted(id);
    }
     
     personList.remove(target);
    */

    // 리스트에서 해당 요소가 있으면 삭제
    // 삭제가 성공이 되면 true를 반환, 실패하면 false를 반환
    boolean removed = personList.removeIf(person -> person.getId() == id);

    if (!removed) {
      return "%d번 사람은 존재하지 않습니다.".formatted(id);
    }

    return "%d번 사람이 삭제되었습니다.".formatted(id);
  }

  @GetMapping("/home/modifyPerson")
  @ResponseBody
  public String modifyPerson(int id, String name, int age) {
    /*
    Person target = null;
    for (Person p : personList) {
      if (p.getId() == id) {
        target = p;
        break;
      }
    }
    */

    Person target = personList.stream()
        .filter(p -> p.getId() == id) // 해당 녀석이 참인 것만 필터링
        .findFirst() // 찾안 것 중에 하나만 남는게, 그 남은 것을 필터링
        .orElse(null); // 없으면 null을 반환

    if (target == null) {
      return "%d번 사람은 존재하지 않습니다.".formatted(id);
    }

    target.setName(name);
    target.setAge(age);

    return "%d번 사람이 수정되었습니다.".formatted(id);
  }

  @GetMapping("/home/showPeople")
  @ResponseBody
  public List<Person> showPeople() {
    return personList;
  }
}

class Article {
  private final int id;
  private String subject;
  private String content;
  private List<Integer> articleNo;

  public Article(int id, String subject, String content, List<Integer> articleNo) {
    this.id = id;
    this.subject = subject;
    this.content = content;
    this.articleNo = articleNo;
  }

  public int getId() {
    return id;
  }

  public String getSubject() {
    return subject;
  }

  public String getContent() {
    return content;
  }

  public List<Integer> getArticleNo() {
    return articleNo;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setArticleNo(List<Integer> articleNo) {
    this.articleNo = articleNo;
  }

  @Override
  public String toString() {
    return "Article{" +
        "id=" + id +
        ", subject='" + subject + '\'' +
        ", content='" + content + '\'' +
        ", articleNo=" + articleNo +
        '}';
  }
}

@Getter
@Setter
@AllArgsConstructor
@ToString
class Article2 {
  private final int id;
  private String subject;
  private String content;
  private List<Integer> articleNo;
}

@AllArgsConstructor
@Getter
@Setter
@ToString
class Person {
  private static int lastId;
  private final int id;
  private String name;
  private int age;

  static {
    lastId = 0;
  }

  public Person(String name, int age) {
    this(++lastId, name, age);
  }
}