package servlet.springservlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 {
    /*
    각 Controller는 이 인터페이스를 구현해서 저마다의 로직을 수행하고,
    Front Controller는 Controller를 호출할 때 이 인터페이스 타입으로 호출해서 여러 가지 구현체를 불러올 수 있게 됨 (다형성)
     */

    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
