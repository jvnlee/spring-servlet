package servlet.springservlet.web.springmvc.v3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import servlet.springservlet.domain.member.Member;
import servlet.springservlet.domain.member.MemberRepository;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    /*
    각 메서드가 ModelAndView가 아닌 String을 반환하게 변경함
    스프링은 반환된 문자열이 논리 이름인 것을 알기 때문에 내부적으로 ModelAndView를 만들고 제시된 논리 이름에 prefix, suffix를 결합시켜 사용함

    ModelAndView를 메서드 안에서 다루지 않기 때문에 동적인 데이터가 포함되어야 하는 경우, 메서드 파라미터로 Model을 받아와 데이터를 넣어주어야함
    데이터가 들어간 Model은 이후 스프링이 내부적으로 각 ModelAndView 생성 시에 활용함

    특정 HTTP 메서드로만 요청을 할 수 있도록 제한함 (2가지 방법 존재, 예시는 GET으로 제한하는 상황)
    1. @RequestMapping(value = "new-form", method = RequestMethod.GET)
    2. @GetMapping("/new-form")
    */

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/new-form")
    public String newForm() {
        return "new-form";
    }

    @PostMapping("/save")
    public String save(@RequestParam("username") String username, @RequestParam("age") int age, Model model) {
        /*
        @RequestParam을 사용해 HTTP 요청으로 들어온 요청파라미터를 이름으로 꺼내오고 메서드의 파라미터로 직접 받을 수 있음
        GET의 쿼리 파라미터와 POST의 Form 데이터 모두 지원하는 방식
        */

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);

        return "save-result";
    }

    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);

        return "members";
    }

}
