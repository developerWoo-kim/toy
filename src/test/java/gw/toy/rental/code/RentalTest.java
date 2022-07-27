package gw.toy.rental.code;

import gw.toy.member.domain.Member;
import gw.toy.member.repository.MemberRepository;
import gw.toy.rental.domain.RentalApplyItem;
import gw.toy.rental.domain.RentalApplyManage;
import gw.toy.rental.domain.RentalBasket;
import gw.toy.rental.repository.RentalRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
@Transactional
public class RentalTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    RentalRepository rentalRepository;

    @Test
    @DisplayName("장바구니에 담긴 아이템을 다중으로 대여 신청하는 테스트")
    @Rollback(value = false)
    public void basketToApplyTest() {
        Member member = new Member();
        member.setId(1L);

        List<RentalBasket> basketList = rentalRepository.findBasketList(member.getId());
        RentalApplyManage rentalApplyManage = new RentalApplyManage();

        for (RentalBasket rentalBasket : basketList) {
            RentalApplyItem rentalApplyItem = new RentalApplyItem();
            rentalApplyItem.setRentalItem(rentalBasket.getRentalItem());
            rentalApplyItem.setQuantity(rentalBasket.getQuantity());

            rentalApplyManage.addApplyItem(rentalApplyItem);
        }

        rentalApplyManage.createApply(member);

        rentalRepository.createRentalItemApply(rentalApplyManage);
    }
}
