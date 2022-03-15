package servlet.springservlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StreamUtils;
import servlet.springservlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        JSON도 어차피 문자이기 때문에 RequestBodyStringServlet에서의 방식과 동일함
        단, 클라이언트가 보내는 HTTP 메시지의 header 중 content-type이 application/json 일 때는 서블릿이 JSON 파싱을 함
         */
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        // 받아온 데이터를 문자열 형식으로 출력
        System.out.println("messageBody = " + messageBody);

        /*
        받아온 데이터를 JSON 방식으로 규격화 하려면 Jackson 이라는 라이브러리를 사용
        Jackson의 ObjectMapper와 readValue()를 사용하면 읽어온 값을 미리 만들어둔 HelloData 클래스의 스펙에 맞게 객체로 만들어줌
        */
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        System.out.println("helloData.username = " + helloData.getUsername());
        System.out.println("helloData.age = " + helloData.getAge());

        response.getWriter().write("OK");
    }

}
