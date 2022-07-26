package gw.toy.reqandreserv.form;

import gw.toy.reqandreserv.domain.ProgramType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProgramSearch {
    private String programTitle;
    private ProgramType programType;
}
