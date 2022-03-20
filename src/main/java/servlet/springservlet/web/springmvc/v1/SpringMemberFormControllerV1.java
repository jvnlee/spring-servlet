package servlet.springservlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
@Controller
1. 내부에 @Component가 포함되어 있기 때문에 컴포넌트 스캔의 대상이 됨.
2. 클래스 레벨에 @Controller 또는 @RequestMapping이 있으면 RequestMappingHandlerMapping이 해당 클래스를 Handler로 인식하고 매핑함
*/
@Controller
public class SpringMemberFormControllerV1 {

    /*
    @RequestMapping
    메서드에 붙이면 어노테이션에 지정된 URL이 호출되었을 때 해당 메서드를 호출함
    */
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }

}
