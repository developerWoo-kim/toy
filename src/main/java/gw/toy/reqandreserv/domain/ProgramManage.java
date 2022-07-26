package gw.toy.reqandreserv.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class ProgramManage {

    @Id
    @GeneratedValue
    @Column(name = "program_id")
    private Long id; // 프로그램 식별자
    private String programTitle; // 프로그램명

    @OneToMany(mappedBy = "programManage")
    private List<RequestManage> requestManageList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ProgramType programType; // 프로그램 유형

    private String programStatus; // 프로그램 상태
    private long capacity; // 정원
    private String indvdlInfo; // 개인정보 동의 문구

    @Enumerated(EnumType.STRING)
    private DrwtType drwtType; // 추첨 방식
    private long applicant; //신청인원
    private LocalDate startDt; // 프로그램 시작일자
    private LocalDate endDt; //프로그램 종료일자
    private LocalDate receiptStartDt; //접수시작일자
    private LocalDate receiptEndDt; //접수종료일자
    private long regId; // 등록자명
    private LocalDateTime regDt; // 등록일자

    /**
     * 신청 가능한 날짜인지 체크
     */
    public boolean receiptDateCheck() {
        String message = "";
        boolean result = true;
        LocalDate nowDate = LocalDate.now();

        if(nowDate.isBefore(this.receiptStartDt)) {
            result = false;
        }
        if(nowDate.isAfter(this.receiptEndDt)) {
            result = false;
        }

        return result;
    }
    /**
     * 선착순 일경우 모집 정원이 모두 찼는지 체크
     */
    public boolean capacityCheck(long applicants) {
        boolean result = true;

        if(this.drwtType.toString() == "FIRST"){
            if(this.applicant + applicants > this.capacity) {
                result = false;
            }
        }

        return result;
    }
}
