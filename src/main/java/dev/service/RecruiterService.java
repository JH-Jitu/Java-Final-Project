package dev.service;

import dev.domain.RecruiterEntity;
import dev.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@SuppressWarnings("ALL")
@Service
@Transactional
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;

    @Autowired
    public RecruiterService(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    public List getAllRecruiters() {
        return recruiterRepository.getAllRecruiters();
    }



    public void createRecruiter(RecruiterEntity recruiterEntity) {
        recruiterRepository.createRecruiter(recruiterEntity);
    }

    public void updateRecruiter(RecruiterEntity recruiterEntity) {
        recruiterRepository.updateRecruiter(recruiterEntity);
    }

    public void deleteRecruiter(Long recruiterId) {
        recruiterRepository.deleteRecruiter(recruiterId);
    }

    public RecruiterEntity getRecruiter(Long recruiterId) {
        return recruiterRepository.getRecruiter(recruiterId);
    }

    public RecruiterEntity findByEmail(String email) {
        return recruiterRepository.findByEmail(email);
    }
}
