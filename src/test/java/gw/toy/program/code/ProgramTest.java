package gw.toy.program.code;

import gw.toy.member.domain.Grade;
import gw.toy.member.domain.Member;
import gw.toy.member.repository.MemberRepository;
import gw.toy.member.service.MemberService;
import gw.toy.reqandreserv.domain.DrwtType;
import gw.toy.reqandreserv.domain.ProgramManage;
import gw.toy.reqandreserv.domain.ProgramType;
import gw.toy.reqandreserv.repository.ProgramRepository;
import gw.toy.reqandreserv.service.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
@Transactional
public class ProgramTest {

    @Autowired
    ProgramRepository pr;
    @Autowired
    ProgramService ps;
    @Autowired
    MemberService ms;

    @Test
    @DisplayName("프로그램 등록 테스트")
    void save() {
        Member member = new Member();
        member.setId(1L);
        Member findMember = ms.findOne(member);

        ProgramManage pm = new ProgramManage();

        pm.setProgramTitle("장애인 돌돔 교육 1차");
        pm.setProgramType(ProgramType.EDU);
        pm.setCapacity(50);
        pm.setDrwtType(DrwtType.SELECT);
        pm.setIndvdlInfo("개인정보 처리방침");
        pm.setReceiptStartDt(LocalDate.of(2022,7,25));
        pm.setReceiptEndDt(LocalDate.of(2022,7,28));
        pm.setStartDt(LocalDate.of(2022,8,1));
        pm.setEndDt(LocalDate.of(2022,8,12));
        pm.setRegId(findMember.getId());
        pm.setRegDt(LocalDateTime.now());

        ProgramManage saveProgram = ps.save(pm, member);

    }

}
