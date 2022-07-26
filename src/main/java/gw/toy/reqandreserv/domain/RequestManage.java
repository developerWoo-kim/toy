package gw.toy.reqandreserv.domain;

import gw.toy.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter @Setter
public class RequestManage {

    @Id
    @GeneratedValue
    @Column(name = "request_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private ProgramManage programManage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String requestType;
    private long applicants;
    private LocalDate startDt;
    private LocalDate endDt;
    private LocalDateTime detailStartDt;
    private LocalDateTime detailEndDt;
    private String status;
    private LocalDateTime requestDt;

}
