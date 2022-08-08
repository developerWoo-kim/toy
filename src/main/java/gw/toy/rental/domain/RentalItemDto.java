package gw.toy.rental.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RentalItemDto {
    private Long rentalItemId;
    private String classification;
    private String explanation;
    private String name;
    private String purpose;
    private LocalDate RentalPossibleDt;
    private long rentalQuantity;
    private long totalQuantity;
    private long quantity;
}
