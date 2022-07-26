package gw.toy.reqandreserv.controller;

import gw.toy.common.utils.AlertExceptionUtil;
import gw.toy.member.domain.Member;
import gw.toy.member.service.MemberService;
import gw.toy.reqandreserv.domain.ProgramManage;
import gw.toy.reqandreserv.domain.RequestForm;
import gw.toy.reqandreserv.domain.RequestManage;
import gw.toy.reqandreserv.service.ProgramService;
import gw.toy.reqandreserv.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;
    private final ProgramService programService;
    private final MemberService memberService;

    @RequestMapping(value = "/request/{programId}/new")
    public String createRequestForm(HttpServletResponse response, @PathVariable("programId") Long programId, Model model) {
        ProgramManage programManage = new ProgramManage();
        programManage.setId(programId);
        programManage = programService.findOne(programManage);

        List<Member> basicMember = memberService.findBasic();

        if(!programManage.receiptDateCheck()) {
            AlertExceptionUtil.alert(response, "접수기간이 아닙니다.");
        };
        model.addAttribute("members", basicMember);
        model.addAttribute("programManage", programManage);

        return "request/requestForm";
    }

    @PostMapping("/request/save")
    public String createRequest(HttpServletResponse response, RequestForm requestForm) {
        requestService.createRequest(response, requestForm);
        return "redirect:/";
    }

}
