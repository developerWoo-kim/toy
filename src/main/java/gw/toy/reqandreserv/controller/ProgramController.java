package gw.toy.reqandreserv.controller;

import gw.toy.member.domain.Member;
import gw.toy.member.service.MemberService;
import gw.toy.reqandreserv.domain.DrwtType;
import gw.toy.reqandreserv.domain.ProgramForm;
import gw.toy.reqandreserv.domain.ProgramManage;
import gw.toy.reqandreserv.domain.ProgramType;
import gw.toy.reqandreserv.form.ProgramSearch;
import gw.toy.reqandreserv.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService ps;
    private final MemberService ms;

    @GetMapping("/programs/new")
    public String createProgramForm(Model model) {
        List<Member> findAdmin = ms.findAdmin();
        model.addAttribute("members",findAdmin);
        return "program/programForm";
    }

    @GetMapping("/program/list")
    public String programList(@ModelAttribute("programSearch") ProgramSearch programSearch, Model model) {
        List<ProgramManage> findAllProgram = ps.findAllByString(programSearch);
        model.addAttribute("programList", findAllProgram);
        return "/program/programList";
    }

    @PostMapping("/program/save")
    public String save(ProgramForm form) {
        ProgramManage programManage = new ProgramManage();
        programManage.setProgramTitle(form.getProgramTitle());
        programManage.setProgramType(form.getProgramType());
        programManage.setCapacity(form.getCapacity());
        programManage.setDrwtType(form.getDrwtType());
        programManage.setReceiptStartDt(LocalDate.parse(form.getReceiptStartDt()));
        programManage.setReceiptEndDt(LocalDate.parse(form.getReceiptEndDt()));
        programManage.setStartDt(LocalDate.parse(form.getStartDt()));
        programManage.setEndDt(LocalDate.parse(form.getEndDt()));
        programManage.setRegId(form.getMemberId());
        programManage.setRegDt(LocalDateTime.now());

        ProgramManage saveProgram = ps.save(programManage);

        return "redirect:/";
    }
}
