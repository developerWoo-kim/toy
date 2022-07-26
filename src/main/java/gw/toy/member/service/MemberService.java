package gw.toy.member.service;

import gw.toy.member.domain.Grade;
import gw.toy.member.domain.Member;
import gw.toy.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public Member findOne(Member member) {
        Member findMember = memberRepository.findOne(member);

        return findMember;
    }

    @Transactional
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Transactional
    public List<Member> findAdmin() { return memberRepository.findAdmin();}

    @Transactional
    public List<Member> findBasic() { return memberRepository.findBasic();}
}
