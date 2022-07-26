package gw.toy.member.repository;

import gw.toy.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Member member) {
        Member findMember = em.find(Member.class, member.getId());
        return findMember;
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findAdmin() {
        return em.createQuery("select m from Member m where m.grade = 'ADMIN'").getResultList();
    }

    public List<Member> findBasic() {
        return em.createQuery("select m from Member m where m.grade = 'BASIC'").getResultList();
    }

}
