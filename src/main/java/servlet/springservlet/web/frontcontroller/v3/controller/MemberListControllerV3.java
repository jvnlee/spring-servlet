package servlet.springservlet.web.frontcontroller.v3.controller;

import servlet.springservlet.domain.member.Member;
import servlet.springservlet.domain.member.MemberRepository;
import servlet.springservlet.web.frontcontroller.ModelView;
import servlet.springservlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();

        ModelView modelView = new ModelView("members");
        modelView.getModel().put("members", members); // model에 데이터 추가

        return modelView;
    }

}
