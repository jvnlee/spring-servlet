package servlet.springservlet.web.springmvc.legacy;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/springmvc/legacy-controller")
public class LegacyController implements Controller {

    /*
    Controller 인터페이스를 구현하는 방식은 레거시 방식

    @Component 어노테이션을 붙여 애플리케이션 실행 시 컨테이너에 Bean으로 등록해줌
    스프링 MVC에서 요청 URL을 처리하기 위한 컨트롤러(핸들러)를 정하려고 사용하는 HandlerMapping은 여기서는 BeanNameUrlHandlerMapping 이고,
    이 컨트롤러를 실제로 호출해줄 수 있는 어댑터를 정하려고 사용하는 HandlerAdapter는 여기서는 SimpleControllerHandlerAdapter임.
    둘 다 스프링에 내장된 구현체들이기 때문에 별도의 HandlerMapping이나 HandlerAdapter를 구현하지 않고도 알맞은 어댑터를 통해 이 컨트롤러를 호출할 수 있었던 것.
    */

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("LegacyController.handleRequest");
        return null;
    }

}
