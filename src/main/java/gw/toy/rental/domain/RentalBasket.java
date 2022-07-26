package gw.toy.rental.domain;

import gw.toy.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter @Setter
public class RentalBasket {

    @Id
    @GeneratedValue
    private Long rentalBasketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_item_id")
    private RentalItem rentalItem;
    private long quantity;

    public RentalBasket(Member member, RentalItem rentalItem, long quantity) {
        this.member = member;
        this.rentalItem = rentalItem;
        this.quantity = quantity;
    }
}
