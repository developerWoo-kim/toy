package gw.toy.reqandreserv.service;

import gw.toy.common.utils.AlertExceptionUtil;
import gw.toy.member.domain.Member;
import gw.toy.member.service.MemberService;
import gw.toy.reqandreserv.domain.ProgramManage;
import gw.toy.reqandreserv.domain.RequestForm;
import gw.toy.reqandreserv.domain.RequestManage;
import gw.toy.reqandreserv.repository.ProgramRepository;
import gw.toy.reqandreserv.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.net.FileNameMap;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final ProgramService programService;
    private final ProgramRepository programRepository;
    private final RequestRepository requestRepository;


    @Transactional
    public RequestManage save(RequestManage requestManage) {
        requestRepository.save(requestManage);
        return requestManage;
    }

    @Transactional
    public RequestManage createRequest(HttpServletResponse response, RequestForm rf) {
        Member member = new Member();
        member.setId(rf.getMemberId());

        ProgramManage programManage = new ProgramManage();
        programManage.setId(rf.getProgramId());

        programManage = programService.findOne(programManage);
        RequestManage requestManage = new RequestManage();
        if(!programManage.capacityCheck(rf.getApplicants())) {
            AlertExceptionUtil.alert(response, "신청하신 프로그램은 모집이 완료되었습니다. 다음 기회에 신청하여주시기바랍니다.");
        } else {
            programManage.setApplicant(rf.getApplicants());
            programRepository.applicantSave(programManage);

            requestManage.setMember(member);
            requestManage.setProgramManage(programManage);
            requestManage.setApplicants(rf.getApplicants());
            requestManage.setStartDt(LocalDate.parse(rf.getStartDt()));
            requestManage.setEndDt(LocalDate.parse(rf.getEndDt()));

            requestRepository.save(requestManage);
        }

        return requestManage;
    }
}
