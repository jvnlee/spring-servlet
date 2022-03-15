package servlet.springservlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        request.getParameter()는 GET 메서드로 요청을 보내는 URL의 쿼리 파라미터 뿐만 아니라
        POST 메서드로 데이터를 메시지 body에 담아 요청을 보내는 HTML Form의 개별 데이터도 모두 인식할 수 있다.
         */

        System.out.println("--- 전체 파라미터 조회 START ---");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));

        System.out.println();

        System.out.println("--- 단일 파라미터 조회 ---");
        String username = request.getParameter("username");
        System.out.println("username = " + username);
        String age = request.getParameter("age");
        System.out.println("age = " + age);

        System.out.println();

        System.out.println("--- 이름이 같은 복수 파라미터 조회 ---");
        String[] values = request.getParameterValues("username");
        for (String value : values) {
            System.out.println("username = " + value);
        }

        response.getWriter().write("OK");

    }
}
