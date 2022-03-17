package servlet.springservlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyView {

    /*
    V1에서는 Controller에서 RequestDispatcher를 가지고 JSP에 View를 그리는 작업을 넘기는 로직이 계속 중복으로 쓰이는 불편함이 있었음
    따라서 V2부터는 개별 Controller에서 각각의 MyView 객체가 반환되도록 하고, Front Controller가 각 MyView를 가져와 render() 메서드로 JSP에 넘기도록 변경함.
    */

    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

}
