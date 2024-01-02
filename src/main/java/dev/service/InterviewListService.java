package dev.service;

import dev.domain.InterviewListEntity;
import dev.repository.InterviewListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewListService {

    private final InterviewListRepository interviewListRepository;

    @Autowired
    public InterviewListService(InterviewListRepository interviewListRepository) {
        this.interviewListRepository = interviewListRepository;
    }

    public List<InterviewListEntity> getAllInterviews() {
        return interviewListRepository.getAllInterviews();
    }

    public void saveOrUpdateInterview(InterviewListEntity interviewListEntity) {
        interviewListRepository.saveOrUpdateInterview(interviewListEntity);
    }

    public InterviewListEntity getInterviewById(Long id) {
        return interviewListRepository.getInterviewById(id);
    }

    public void deleteInterview(Long id) {
        interviewListRepository.deleteInterview(id);
    }
}
