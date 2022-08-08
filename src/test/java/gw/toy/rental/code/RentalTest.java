package gw.toy.rental.code;

import gw.toy.member.domain.Member;
import gw.toy.member.repository.MemberRepository;
import gw.toy.rental.domain.RentalApplyItem;
import gw.toy.rental.domain.RentalApplyManage;
import gw.toy.rental.domain.RentalBasket;
import gw.toy.rental.repository.RentalRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

        // 대여 신청 객체 생성
        rentalApplyManage.createApply(member, basketList);

        rentalRepository.createRentalItemApply(rentalApplyManage);
    }


    @Test
    @DisplayName("장바구니에 담긴 아이템을 다중으로 대여 신청하는 테스트")
    @Rollback(value = false)
    public void cascadeRemoveTest() {
        Member member = new Member();
        member.setId(1L);
        List<RentalBasket> basketList = rentalRepository.findBasketList(member.getId());
        RentalApplyManage rentalApplyManage = new RentalApplyManage();

        // 대여 신청 객체 생성
        rentalApplyManage.createApply(member, basketList);

        // 바구니 비우기
        for (RentalBasket rentalBasket : basketList) {
            rentalRepository.removeBasket(rentalBasket);
        }
        // 대여 신청
        rentalRepository.createRentalItemApply(rentalApplyManage);
    }

    @Test
    @DisplayName("일자별 대여 가능한 물품 목록 조회 테스트")
    void findPossibleItemListTest() {
//        rentalRepository.findPossibleItemList(LocalDate.of(2022,8,3), LocalDate.of(2022,8,5));
    }
}
