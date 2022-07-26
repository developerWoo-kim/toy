package gw.toy.reqandreserv.repository;

import gw.toy.reqandreserv.domain.RequestManage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class RequestRepository {
    private final EntityManager em;

    public void save(RequestManage requestManage) {
        em.persist(requestManage);
    }
}
