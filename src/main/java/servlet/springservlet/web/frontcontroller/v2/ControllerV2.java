package servlet.springservlet.web.frontcontroller.v2;

import servlet.springservlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

    // V1에서는 반환 타입이 void 였으나, MyView 객체를 반환하는 것으로 변경함
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
