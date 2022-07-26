package gw.toy.rental.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class RentalItem {

    @Id
    @GeneratedValue
    private Long rentalItemId;

    @OneToMany(mappedBy = "rentalItem")
    private List<RentalBasket> rentalBasketList = new ArrayList<>();

    private String name;
    private String classification; //타입
    private String purpose;
    private long rentalQuantity;
    private long totalQuantity;
    private String explanation;
    private LocalDate rentalPossibleDt; // 최대 대여 가능 기간
}
