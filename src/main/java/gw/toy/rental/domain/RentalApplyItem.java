package gw.toy.rental.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Getter @Setter
public class RentalApplyItem {

    @Id
    @GeneratedValue
    private Long applyItemId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "rental_item_id")
    private RentalItem rentalItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_apply_id")
    private RentalApplyManage rentalApplyManage;

    private long quantity;
    private LocalDate startDt;
    private LocalDate endDt;

}
