package gw.toy.rental.repository;

import gw.toy.rental.domain.RentalBasket;
import gw.toy.rental.domain.RentalItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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
     * @param id
     * @return
     */
    public List<RentalBasket> findBasketList(Long id) {
        return em.createQuery("select b from RentalBasket b where b.memberId = :id").getResultList();
    }

    /**
     * 장바구니에서 아이템 조회
     * @param id
     * @return
     */
    public RentalBasket findItemInBasket(Long id) {
        RentalBasket rentalBasket = em.find(RentalBasket.class, id);
        return rentalBasket;
    }

    /**
     * 대여 물품 장바구니 담기
     * @param rentalBasket
     */
    public void addItemToBasket(RentalBasket rentalBasket) {
        em.persist(rentalBasket);
    }
}
