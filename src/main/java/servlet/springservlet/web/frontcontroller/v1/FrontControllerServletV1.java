package servlet.springservlet.web.frontcontroller.v1;

import servlet.springservlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import servlet.springservlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import servlet.springservlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    /*
    이 Servlet이 Front Controller로서 모든 요청을 먼저 받은 후, 공통 로직이 있다면 수행하고, URI에 따라 실제 처리를 담당하는 Controller를 호출함
    아래의 Map도 URI와 담당 Controller를 Mapping하는 용도
    */
    private Map<String, ControllerV1> controllerV1Map = new HashMap<>();

    public FrontControllerServletV1() {
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        // 요청이 들어온 URI를 가지고 Map에서 알맞은 Controller를 꺼냄
        ControllerV1 controller = controllerV1Map.get(requestURI);

        // URI에 매치되는 Controller가 없을 시, 404 응답
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Controller 호출 (request와 response 객체를 넘겨줌)
        controller.process(request, response);
    }

}
