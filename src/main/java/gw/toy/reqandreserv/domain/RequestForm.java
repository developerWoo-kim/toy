package gw.toy.reqandreserv.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class RequestForm {
    private long applicants;
    private String startDt;
    private String endDt;
    private long programId;
    private long memberId;
}
