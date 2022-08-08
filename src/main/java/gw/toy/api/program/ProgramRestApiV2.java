package gw.toy.api.program;

import gw.toy.api.exception.ApiException;
import gw.toy.api.exception.ExceptionEnum;
import gw.toy.reqandreserv.domain.ProgramManage;
import gw.toy.reqandreserv.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequiredArgsConstructor
public class ProgramRestApiV2 {

    private final ProgramService ps;

    /**
     * REST API
     * All program api
     * version : v2
     * versioning : path
     *
     * @return List<ProgramManage>
     */
    @GetMapping("/v2/AllProgram")
    public List<ProgramManage> retrieveAllProgram() {
        ProgramManage pm = new ProgramManage();
        List<ProgramManage> programs = ps.findAll(pm);

        return programs;
    }

    /**
     * REST API
     * program api
     * version : v2
     * versioning : path
     *
     * @return ProgramManage
     */
    @GetMapping("/v2/program/{id}")
    public EntityModel<ProgramManage> retrieveProgram(@PathVariable(value = "id") Long id) {
        ProgramManage pm = new ProgramManage();
        pm.setId(id);
        pm = ps.findOne(pm);

        if(pm == null) {
            throw new ApiException(ExceptionEnum.PROGRAM_NOTFOUND_EXCEPTION);
        }

        EntityModel<ProgramManage> model = EntityModel.of(
                pm,
                linkTo(methodOn(this.getClass()).retrieveProgram(id)).withSelfRel(),
                linkTo(methodOn(this.getClass()).retrieveAllProgram()).withRel("all-users")
        );

        return model;
    }
}

