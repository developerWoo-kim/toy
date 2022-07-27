package gw.toy.rental.service;

import com.google.gson.JsonObject;
import gw.toy.member.domain.Member;
import gw.toy.member.repository.MemberRepository;
import gw.toy.rental.domain.RentalApplyItem;
import gw.toy.rental.domain.RentalApplyManage;
import gw.toy.rental.domain.RentalBasket;
import gw.toy.rental.domain.RentalItem;
import gw.toy.rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final MemberRepository memberRepository;
    private final RentalRepository rentalRepository;

    @Transactional
    public RentalItem findOne(Long itemId) {
        RentalItem findOne = rentalRepository.findOne(itemId);
        return findOne;
    }

    @Transactional
    public List<RentalItem> findAll() {
        List<RentalItem> findAll = rentalRepository.findAll();
        return findAll;
    }

    @Transactional
    public void save(RentalItem rentalItem) {
        rentalRepository.save(rentalItem);
    }

    @Transactional
    public List<RentalBasket> findBasketList(Long memberId) {
        Member member = new Member();
        member.setId(memberId);

        RentalBasket rentalBasket = new RentalBasket();
        rentalBasket.setMember(member);

        return rentalRepository.findBasketList(memberId);
    }
    @Transactional
    public String addItemToBasket(Long id) {
        JsonObject json = new JsonObject();
        Optional<RentalBasket> itemInBasket = rentalRepository.findItemInBasket(id);

        if(itemInBasket.isEmpty()) {
            Member member = new Member();
            member.setId(1L);
            RentalItem rentalItem = new RentalItem();
            rentalItem.setRentalItemId(id);

            RentalBasket rentalBasket = new RentalBasket(member, rentalItem, 2);
            rentalRepository.addItemToBasket(rentalBasket);

            json.addProperty("status", "success");
            json.addProperty("message", "장바구니에 담겼습니다.");
        } else {
            json.addProperty("status", "fail");
            json.addProperty("message", "이미 장바구니에 존재합니다.");
        }
        return json.toString();
    }

//    @Transactional
//    public void applyRentalItem(Long memberId, RentalBasket rentalBasket) {
//        Member member = new Member();
//        member.setId(memberId);
//
//        // 신청 테이블로 insert
//        RentalApplyManage rentalApplyManage = new RentalApplyManage();
//        rentalApplyManage.setApplyDate(LocalDate.now());
//        rentalApplyManage.setMember(member);
//        rentalRepository.addApply(rentalApplyManage);
//        Long rentalApplyId = rentalApplyManage.getRentalApplyId();
//
//        // 바구니에서 신청 물품 테이블로 insert
//        Optional<RentalBasket> rentalItemList = rentalRepository.findItemInBasket(memberId);
//        for (Object o : ) {
//
//        }
//        RentalApplyItem rentalApplyItem = new RentalApplyItem();
//
//        rentalRepository.addToApplyItem();
//
//        // 물품 테이블에서 대여 물품 수량 update
//        rentalRepository.updateItemQuantity();
//        // 장바구니 delete
//        rentalRepository.removeBasket();
//    }
}
