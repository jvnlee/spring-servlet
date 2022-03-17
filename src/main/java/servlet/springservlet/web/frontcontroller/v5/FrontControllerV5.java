package servlet.springservlet.web.frontcontroller.v5;

import servlet.springservlet.web.frontcontroller.ModelView;
import servlet.springservlet.web.frontcontroller.MyView;
import servlet.springservlet.web.frontcontroller.v3.ControllerV3;
import servlet.springservlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import servlet.springservlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import servlet.springservlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import servlet.springservlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import servlet.springservlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import servlet.springservlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import servlet.springservlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import servlet.springservlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerV5 extends HttpServlet {

    private Map<String, Object> handlerMap = new HashMap<>();
    private List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerV5() {
        initHandlerMap();
        initHandlerAdapters();
    }

    private void initHandlerMap() {
        handlerMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HTTP 요청이 들어오면 handlerMap으로부터 요청 URI에 알맞은 handler를 가져옴
        Object handler = getHandler(request);

        // 알맞은 handler가 없다면 404 응답
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 가져온 handler에 맞는 adapter를 찾기 위해 handlerAdapters를 순회해서 가져옴 (없으면 예외 발생)
        MyHandlerAdapter handlerAdapter = getHandlerAdapter(handler);

        // adapter의 handle() 메서드에 request, response 객체와 handler를 넘겨 내부적으로 서비스 로직을 수행하고 ModelView 객체를 반환 받음
        ModelView modelView = handlerAdapter.handle(request, response, handler);

        // 반환 받은 ModelView 객체로부터 view의 논리 이름을 얻고, viewResolver()에 넣어 MyView 객체를 반환 받음
        String viewName = modelView.getViewName();
        MyView view = viewResolver(viewName);

        // MyView 객체의 render()를 호출해 request 객체에 model의 데이터를 넣고 최종적으로 request와 response 객체를 JSP로 forward 시킴
        view.render(modelView.getModel(), request, response);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter handlerAdapter : handlerAdapters) {
            if (handlerAdapter.supports(handler)) {
                return handlerAdapter;
            }
        }
        throw new IllegalArgumentException(handler + "에 대한 Handler Adapter를 찾을 수 없습니다.");
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}
