package dev.rest;

import dev.domain.InterviewListEntity;
import dev.service.InterviewListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interviews")
public class InterviewListController {

    private final InterviewListService interviewListService;

    @Autowired
    public InterviewListController(InterviewListService interviewListService) {
        this.interviewListService = interviewListService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<InterviewListEntity>> getAllInterviews() {
        List<InterviewListEntity> interviews = interviewListService.getAllInterviews();
        return new ResponseEntity<>(interviews, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> saveOrUpdateInterview(@RequestBody InterviewListEntity interviewListEntity) {
        interviewListService.saveOrUpdateInterview(interviewListEntity);
        return new ResponseEntity<>("Interview saved/updated successfully", HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<InterviewListEntity> getInterviewById(@PathVariable Long id) {
        InterviewListEntity interview = interviewListService.getInterviewById(id);
        return new ResponseEntity<>(interview, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteInterview(@PathVariable Long id) {
        interviewListService.deleteInterview(id);
        return new ResponseEntity<>("Interview deleted successfully", HttpStatus.OK);
    }
}
