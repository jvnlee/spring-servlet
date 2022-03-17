package servlet.springservlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

    // V4 Controller는 ModelView 객체 대신, view의 논리 이름만 반환함
    String process(Map<String, String> paramMap, Map<String, Object> model);

}
