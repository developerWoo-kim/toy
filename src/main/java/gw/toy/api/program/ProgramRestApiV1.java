package gw.toy.api.program;

import gw.toy.reqandreserv.domain.ProgramManage;
import gw.toy.reqandreserv.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProgramRestApiV1 {

    private final ProgramService ps;

    /**
     * REST API
     * All program api
     * version : v1
     * versioning : path
     *
     * @return List<ProgramManage>
     */
    @GetMapping("/v1/AllProgram")
    public List<ProgramManage> retrieveAllProgram() {
        ProgramManage pm = new ProgramManage();
        List<ProgramManage> programs = ps.findAll(pm);
        return programs;
    }

    /**
     * REST API
     * program api
     * version : v1
     * versioning : path
     *
     * @return ProgramManage
     */
    @GetMapping("/v1/program/{id}")
    public ProgramManage retrieveProgram(@PathVariable(value = "id") Long id) {
        ProgramManage pm = new ProgramManage();
        pm.setId(id);
        pm = ps.findOne(pm);
        return pm;
    }
}

