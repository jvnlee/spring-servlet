package servlet.springservlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloData {
    // HTTP 요청에서 받아온 JSON 데이터를 문자열이 아닌 객체에 담기 위해 데이터의 스펙과 동일한 구조의 클래스를 만듦

    private String username;
    private int age;

}
