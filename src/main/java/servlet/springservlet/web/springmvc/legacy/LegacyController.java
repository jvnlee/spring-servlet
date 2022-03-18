package servlet.springservlet.web.springmvc.legacy;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/springmvc/legacy-controller")
public class LegacyController implements Controller {

    /*
    ModelAndView 인스턴스를 반환하도록 변경

    스프링 부트는 애플리케이션 실행 시 InternalResourceViewResolver라는 뷰 리졸버를 자동으로 등록하는데,
    뷰 리졸버는 application.properties에 미리 지정했던 view 경로의 prefix와 suffix를 사용해서
    ModelAndView 인스턴스의 논리 이름에 앞뒤로 prefix과 suffix가 붙여 완전한 물리 경로를 완성시켜줌

    뷰 리졸버는 이러한 내부 로직 이후 InternalResourceView 인스턴스를 반환하고
    DispatcherServlet이 이 인스턴스에 대한 render()를 호출해 JSP로 forward함
    (InternalResourceView는 View 인터페이스의 구현체 중 하나로, JSP 방식을 사용하기 위해 forward()가 있지만
    나머지 View 구현체들은 JSP를 안쓰기 때문에 forward() 없이 바로 뷰 렌더링으로 들어감)
    */

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("LegacyController.handleRequest");
        return new ModelAndView("new-form");
    }

}
