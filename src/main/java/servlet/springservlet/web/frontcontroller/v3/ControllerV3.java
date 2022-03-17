package servlet.springservlet.web.frontcontroller.v3;

import servlet.springservlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    /*
    이제 각 Controller는 HttpServletRequest나 HttpServletResponse 객체에 대해 몰라도 됨 (Servlet 관련 종속성이 사라졌음)
    그 대신 Front Controller가 받은 요청으로부터 생성해준 paramMap을 넘겨 받아 로직을 수행하면 됨
    */
    ModelView process(Map<String, String> paramMap);

}
