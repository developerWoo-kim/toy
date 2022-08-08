package gw.toy.rental.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gw.toy.rental.domain.*;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RentalRepository {

    private final EntityManager em;

    /**
     * 대여 물품 단건 조회
     * @param itemId
     * @return
     */
    public RentalItem findOne(Long itemId) {
        RentalItem rentalItem = em.find(RentalItem.class, itemId);
        return rentalItem;
    }
    /**
     * 대여 물품 다건 조회
     */
    public List<RentalItem> findAll() {
        return em.createQuery("select r from RentalItem r").getResultList();
    }

    /**
     * 대여 물품 저장
     * @param item
     */
    public void save(RentalItem item) {
        em.persist(item);
    }

    /**
     * 장바구니 조회
     * @param memberId
     * @return
     */
    public List<RentalBasket> findBasketList(Long memberId) {
        String jpql = "select b from RentalBasket b where ";
        jpql += "b.member.id = :memberId";

        TypedQuery<RentalBasket> query = em.createQuery(jpql, RentalBasket.class)
                .setMaxResults(1000);

        query = query.setParameter("memberId", memberId);

        return query.getResultList();
    }

    /**
     * 장바구니에서 아이템 조회
     * @param rentalItemId
     * @return
     */
    public Optional<RentalBasket> findItemInBasket(Long rentalItemId) {
        String jpql = "select b from RentalBasket b where ";
        jpql += "b.rentalItem.rentalItemId = :rentalItemId";

        List<RentalBasket> rentalBasket = em.createQuery(jpql, RentalBasket.class)
                .setMaxResults(1000)
                .setParameter("rentalItemId", rentalItemId)
                .getResultList();
        return rentalBasket.stream().findAny();
    }

    /**
     * 대여 물품 장바구니 담기
     * @param rentalBasket
     */
    public void addItemToBasket(RentalBasket rentalBasket) {
        em.persist(rentalBasket);
    }

    /**
     * 장바구니 비우기
     */
    public void removeBasket(RentalBasket rentalBasket) {
        em.remove(rentalBasket);
    }

    /**
     * 선택한 일자에 대여 가능한 물품이 있는지 확인
     */
//    public List<RentalItemDto> findPossibleItemList(LocalDate startDt, LocalDate endDt) {
//        RentalItem rentalItem = new RentalItem();
//        em.persist(rentalItem);
//
//        JPAQueryFactory query = new JPAQueryFactory(em);
//
//
//
//        return
//    }

    /**
     * 대여 물품 신청
     * @param rentalApplyManage
     */
    public void createRentalItemApply(RentalApplyManage rentalApplyManage) {
        em.persist(rentalApplyManage);
    }

    /**
     * 대여 물품 신청관리 테이블 조회
     */
    public RentalApplyManage findRentalApplyManage(Long rentalApplyId) {
        RentalApplyManage rentalApplyManage = em.find(RentalApplyManage.class, rentalApplyId);
        return rentalApplyManage;
    }
}
