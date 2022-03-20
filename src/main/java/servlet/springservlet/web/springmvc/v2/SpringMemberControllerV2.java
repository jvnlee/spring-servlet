package servlet.springservlet.web.springmvc.v2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import servlet.springservlet.domain.member.Member;
import servlet.springservlet.domain.member.MemberRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    /*
    @RequestMapping은 메서드 단위로 붙일 수 있기 때문에 연관성이 있는 메서드들이라면 한 컨트롤러 안에 모을 수 있음 (통합)
    클래스에 @RequestMapping을 추가적으로 붙이면 각 메서드의 어노테이션에 중복되던 URL을 생략할 수 있음
    */

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView modelView = new ModelAndView("save-result");
        modelView.addObject("member", member);

        return modelView;
    }

    @RequestMapping
    public ModelAndView members() {
        List<Member> members = memberRepository.findAll();

        ModelAndView modelView = new ModelAndView("members");
        modelView.addObject("members", members);

        return modelView;
    }
}
