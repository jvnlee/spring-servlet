package servlet.springservlet.web.frontcontroller.v3.controller;

import servlet.springservlet.domain.member.Member;
import servlet.springservlet.domain.member.MemberRepository;
import servlet.springservlet.web.frontcontroller.ModelView;
import servlet.springservlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView modelView = new ModelView("save-result");
        modelView.getModel().put("member", member); // model에 데이터 추가

        return modelView;
    }

}
