package gw.toy.reqandreserv.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProgramForm {

    private String programTitle; // 프로그램명
    private ProgramType programType; // 프로그램 유형
    private long capacity; // 정원
    private DrwtType drwtType; // 추첨 방식
    private String startDt; // 프로그램 시작일자
    private String endDt; //프로그램 종료일자
    private String receiptStartDt; //접수시작일자
    private String receiptEndDt; //접수종료일자

    private long memberId;
}
