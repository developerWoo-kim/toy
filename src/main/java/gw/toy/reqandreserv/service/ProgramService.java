package gw.toy.reqandreserv.service;

import gw.toy.member.domain.Member;
import gw.toy.reqandreserv.domain.ProgramManage;
import gw.toy.reqandreserv.form.ProgramSearch;
import gw.toy.reqandreserv.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository programRepository;

    @Transactional
    public ProgramManage save(ProgramManage programManage) {
        programRepository.save(programManage);
        return programManage;
    }

    @Transactional
    public ProgramManage findOne(ProgramManage programManage) {
        ProgramManage findProgram = programRepository.findOne(programManage);
        return findProgram;
    }

    @Transactional
    public List<ProgramManage> findAll() {
        List<ProgramManage> findAllProgram = programRepository.findAll();
        return findAllProgram;
    }

    @Transactional
    public List<ProgramManage> findAllByString(ProgramSearch programSearch) {
        List<ProgramManage> findProgram = programRepository.findAllByString(programSearch);

        return findProgram;
    }
}
