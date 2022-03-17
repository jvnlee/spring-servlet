package servlet.springservlet.web.frontcontroller.v3;

import servlet.springservlet.web.frontcontroller.ModelView;
import servlet.springservlet.web.frontcontroller.MyView;
import servlet.springservlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import servlet.springservlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import servlet.springservlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();

    public FrontControllerServletV3() {
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerV3Map.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // HTTP 요청으로 들어온 데이터를 해석해 각 항목을 Map에 담음
        Map<String, String> paramMap = createParamMap(request);

        // Controller에게 paramMap을 넘기고, 로직 수행이 된 후 반환되는 ModelView 객체를 받아옴
        ModelView modelView = controller.process(paramMap);

        // ModelView 객체에서 논리 이름인 viewName을 꺼냄
        String viewName = modelView.getViewName(); // new-form과 같은 논리 이름. 전체 경로로 표현되는 이름이 아님

        // viewName을 가지고 서버 내의 물리적인 JSP 경로로 만든 뒤, 그걸 가지고 MyView 객체를 생성함
        MyView view = viewResolver(viewName);

        // MyView의 render()를 호출해서 JSP에 View 렌더링을 의뢰함
        view.render(modelView.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        // 이렇게 함으로써 서버 내의 JSP 경로나 디렉토리 구조가 바뀌더라도 V2에서처럼 각 Controller 내부를 수정하지 않고, 여기 명시된 경로만 수정하면 됨
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

}
