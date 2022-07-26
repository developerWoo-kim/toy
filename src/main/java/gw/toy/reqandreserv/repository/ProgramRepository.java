package gw.toy.reqandreserv.repository;

import gw.toy.reqandreserv.domain.ProgramManage;
import gw.toy.reqandreserv.form.ProgramSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProgramRepository {

    private final EntityManager em;

    public ProgramManage findOne(ProgramManage programManage) {
        ProgramManage findProgram = em.find(ProgramManage.class, programManage.getId());
        return findProgram;
    }

    public List<ProgramManage> findAll(ProgramManage programManage) {
        return em.createQuery("select p from ProgramManage p").getResultList();
    }

    public List<ProgramManage> findAllByString(ProgramSearch programSearch) {
        String jpql = "select p from ProgramManage p";
        boolean isFirstCondition = true;

        if (programSearch.getProgramTitle() != null && !"".equals(programSearch.getProgramTitle())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " p.programTitle like :programTitle";
        }

        if (programSearch.getProgramType() != null && !"".equals(programSearch.getProgramType())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " p.programType = :programType";
        }

        TypedQuery<ProgramManage> query = em.createQuery(jpql, ProgramManage.class)
                .setMaxResults(1000);

        if(programSearch.getProgramTitle() != null && !"".equals(programSearch.getProgramTitle())) {
            query = query.setParameter("programTitle", "%" + programSearch.getProgramTitle() + "%");
        }

        if(programSearch.getProgramType() != null && !"".equals(programSearch.getProgramType())) {
            query = query.setParameter("programType", programSearch.getProgramType());
        }

        return query.getResultList();
    }

    public void save(ProgramManage programManage) {
        em.persist(programManage);
    }

    public void applicantSave(ProgramManage programManage) {
        em.persist(programManage);
    }
}
