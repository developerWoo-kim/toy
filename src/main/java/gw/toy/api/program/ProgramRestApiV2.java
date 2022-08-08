package gw.toy.api.program;

import gw.toy.api.exception.ApiException;
import gw.toy.api.exception.ExceptionEnum;
import gw.toy.reqandreserv.domain.ProgramForm;
import gw.toy.reqandreserv.domain.ProgramManage;
import gw.toy.reqandreserv.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<EntityModel<ProgramManage>> retrieveAllProgram() {

        List<EntityModel<ProgramManage>> pmList = ps.findAll().stream()
                .map(pm -> {
                    return EntityModel.of(pm,
                            linkTo(methodOn(this.getClass()).retrieveAllProgram()).withSelfRel(),
                            linkTo(methodOn(this.getClass()).retrieveProgram(pm.getId())).withRel("findOne")
                            );
                }).collect(Collectors.toList());

        return pmList;
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

    /**
     * REST API
     * program save api
     * version : v2
     * versioning : path
     *
     * @return ResponseEntity
     */
    @PostMapping("/v2/program")
    public ResponseEntity<?> saveProgram(@RequestBody ProgramForm form) {

        ProgramManage programManage = new ProgramManage(form);
        ProgramManage saveProgram = ps.save(programManage);

        EntityModel<ProgramManage> model = EntityModel.of(
                saveProgram,
                linkTo(methodOn(this.getClass()).retrieveProgram(saveProgram.getId())).withSelfRel()
        );

        return ResponseEntity
                .created(model.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(model);
    }
}

