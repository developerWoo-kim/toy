package gw.toy.member.code;

import gw.toy.member.domain.Grade;
import gw.toy.member.domain.Member;
import gw.toy.member.repository.MemberRepository;
import gw.toy.member.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입후 조회 테스트")
    public void save() {
        Member member = new Member();
        member.setName("이영민");
        member.setGrade(Grade.BASIC);

        Long memberId = memberService.save(member);
        member.setId(memberId);
    }
}
