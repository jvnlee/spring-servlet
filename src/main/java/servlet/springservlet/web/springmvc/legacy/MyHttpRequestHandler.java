package servlet.springservlet.web.springmvc.legacy;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("/springmvc/request-handler")
public class MyHttpRequestHandler implements HttpRequestHandler {

    /*
    또 다른 레거시 방식의 컨트롤러 사용 예시 (HttpRequestHandler 인터페이스 구현)

    @Component 어노테이션을 붙여 Bean으로 등록해주고, 이름은 요청 URL과 동일하게 세팅
    BeanNameUrlHandlerMapping(내장)을 통해 이 Bean이 요청을 처리할 컨트롤러임을 인식함.
    그리고 컨트롤러를 실제로 호출해줄 어댑터인 HttpRequestHandlerAdapter(내장)을 반환함.
    DispatchServlet(프론트 컨트롤러)가 이 어댑터의 handle()을 호출하면 handle()의 내부 로직으로 해당 컨트롤러를 호출할 수 있게 됨
    */

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequestHandler.handleRequest");
    }

}
