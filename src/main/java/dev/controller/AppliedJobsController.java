package dev.controller;

import dev.domain.AppliedJobsEntity;
import dev.service.AppliedJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@SuppressWarnings("CallToPrintStackTrace")
@RestController
@RequestMapping("/apply-jobs")
public class AppliedJobsController {

    private final AppliedJobsService appliedJobsService;

    @Autowired
    public AppliedJobsController(AppliedJobsService appliedJobsService) {
        this.appliedJobsService = appliedJobsService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAppliedJob(@RequestBody AppliedJobsEntity appliedJobsEntity) {
        try {
            appliedJobsService.createAppliedJob(appliedJobsEntity);
            return new ResponseEntity<>("Applied job created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create applied job", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAppliedJob(@RequestBody AppliedJobsEntity appliedJobsEntity) {
        try {
            appliedJobsService.updateAppliedJob(appliedJobsEntity);
            return new ResponseEntity<>("Applied job updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update applied job", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<AppliedJobsEntity>> getAllAppliedJobs() {
        try {
            List<AppliedJobsEntity> appliedJobs = appliedJobsService.getAllAppliedJobs();
            return new ResponseEntity<>(appliedJobs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{appliedJobId}")
    public ResponseEntity<String> deleteAppliedJob(@PathVariable Long appliedJobId) {
        try {
            appliedJobsService.deleteAppliedJob(appliedJobId);
            return new ResponseEntity<>("Applied job deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete applied job", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find-by-email/{email}")
    public ResponseEntity<AppliedJobsEntity> findByEmail(@PathVariable String email) {
        try {
            AppliedJobsEntity appliedJobsEntity = appliedJobsService.findByEmail(email);
            return new ResponseEntity<>(appliedJobsEntity, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
