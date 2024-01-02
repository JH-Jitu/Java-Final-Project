package dev.service;

import dev.domain.AppliedJobsEntity;
import dev.repository.AppliedJobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppliedJobsService {

    private final AppliedJobsRepository appliedJobsRepository;

    @Autowired
    public AppliedJobsService(AppliedJobsRepository appliedJobsRepository) {
        this.appliedJobsRepository = appliedJobsRepository;
    }

    public void createAppliedJob(AppliedJobsEntity appliedJobsEntity) {
        appliedJobsRepository.createAppliedJob(appliedJobsEntity);
    }

    public void updateAppliedJob(AppliedJobsEntity appliedJobsEntity) {
        appliedJobsRepository.updateAppliedJob(appliedJobsEntity);
    }

    public List<AppliedJobsEntity> getAllAppliedJobs() {
        return appliedJobsRepository.getAllAppliedJobs();
    }

    public void deleteAppliedJob(Long appliedJobId) {
        appliedJobsRepository.deleteAppliedJob(appliedJobId);
    }

    public AppliedJobsEntity findByEmail(String email) {
        return appliedJobsRepository.findByEmail(email);
    }
}
