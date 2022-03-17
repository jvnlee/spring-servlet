package servlet.springservlet.web.frontcontroller.v5;

import servlet.springservlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {

    // 파라미터로 넘어온 handler(= Controller)가 사용 가능한 것인지 판별
    boolean supports(Object handler);

    //
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;

}
