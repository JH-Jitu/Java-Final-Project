package dev.service;

import dev.domain.AvailableJobsEntity;
import dev.repository.AvailableJobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@SuppressWarnings("ALL")
@Service
@Transactional
public class AvailableJobsService {

    private final AvailableJobsRepository availableJobsRepository;

    @Autowired
    public AvailableJobsService(AvailableJobsRepository availableJobsRepository) {
        this.availableJobsRepository = availableJobsRepository;
    }

    public void createAvailableJob(AvailableJobsEntity availableJobsEntity) {
        availableJobsRepository.createAvailableJob(availableJobsEntity);
    }

    public void updateAvailableJob(AvailableJobsEntity availableJobsEntity) {
        availableJobsRepository.updateAvailableJob(availableJobsEntity);
    }

    public List<AvailableJobsEntity> getAllAvailableJobs() {
        return availableJobsRepository.getAllAvailableJobs();
    }


    public AvailableJobsEntity getAvailableJobById(Long id) {
        return availableJobsRepository.getAvailableJobById(id);
    }

    public void deleteAvailableJob(Long id) {
        availableJobsRepository.deleteAvailableJob(id);
    }
}
