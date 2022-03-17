package servlet.springservlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {

    /*
    V2에서는 별도의 Model 없이 Controller가 직접 MyView를 반환하고, Front Controller가 받아온 MyView를 JSP에 forward 해줬음

    그러나 V3에서는 Controller가 View의 논리 이름과 데이터를 담은 ModelView를 반환하게 함
    Front Controller는 각 Controller로부터 ModelView 객체를 받아와 객체에 포함된 논리 이름(viewName)에 물리적 경로를 덧붙인 viewPath를 만듦
    해당 viewPath를 가지고 MyView 객체를 생성하고, ModelView에 있던 model(데이터)과 request, response 객체 3개를 render() 파라미터로 넘김
    render()는 Model에 있던 데이터를 전부 뽑아내어 request 객체의 attribute으로 넣고, 최종적으로 request와 response 객체를 JSP로 forward 함으로써 실제 View를 그리도록 시킴
    */

    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        modelDataToRequestAttribute(model, request); // Model에 있던 데이터를 모두 request의 attribute에 담음
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    private void modelDataToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((k, v) -> request.setAttribute(k, v));
    }

}
