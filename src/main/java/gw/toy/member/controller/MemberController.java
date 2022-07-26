package gw.toy.member.controller;

import gw.toy.member.domain.Grade;
import gw.toy.member.domain.Member;
import gw.toy.member.domain.MemberForm;
import gw.toy.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping("/members/new")
    public String createMemberForm(Model model) {
        MemberForm memberForm = new MemberForm();
        model.addAttribute("memberForm", memberForm);
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String createMember(@Valid MemberForm memberForm, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Member member = new Member();
        member.setName(memberForm.getName());
        member.setGrade(memberForm.getGrade());

        memberService.save(member);

        return "redirect:/members";
    }

}
