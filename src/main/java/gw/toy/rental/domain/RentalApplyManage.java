package gw.toy.rental.domain;

import gw.toy.member.domain.Member;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class RentalApplyManage {

    @Id
    @GeneratedValue
    private Long rentalApplyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "rentalApplyManage",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RentalApplyItem> rentalApplyItems = new ArrayList<>();

    private LocalDate applyDate;
    @Enumerated(EnumType.STRING)
    private RentalApplyStatus status;

    /**
     * 대여아이템을 추가하는 편의 메소드
     * @param rentalApplyItem
     */
    public void addApplyItem(RentalApplyItem rentalApplyItem) {
        this.rentalApplyItems.add(rentalApplyItem);
        rentalApplyItem.setRentalApplyManage(this);
    }

    /**
     * 대여 신청 객체를 만드는 편의 메소드
     * @param member
     */
    public void createApply(Member member, List<RentalBasket> basket) {
        this.member = member;
        this.applyDate = LocalDate.now();
        this.status = RentalApplyStatus.WAIT;

        for (RentalBasket rentalBasket : basket) {
            RentalApplyItem rentalApplyItem = new RentalApplyItem();
            rentalApplyItem.setRentalItem(rentalBasket.getRentalItem());
            rentalApplyItem.setQuantity(rentalBasket.getQuantity());
            rentalApplyItem.setStartDt(LocalDate.of(2022,8,5));
            rentalApplyItem.setEndDt(LocalDate.of(2022,8,8));

            addApplyItem(rentalApplyItem);
        }
    }

}
