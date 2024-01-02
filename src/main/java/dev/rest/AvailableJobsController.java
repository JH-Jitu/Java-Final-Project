package dev.rest;

import dev.domain.AvailableJobsEntity;
import dev.service.AvailableJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@SuppressWarnings("ALL")
@RestController
@RequestMapping("/jobs")
public class AvailableJobsController {

    private final AvailableJobsService availableJobsService;

    @Autowired
    public AvailableJobsController(AvailableJobsService availableJobsService) {
        this.availableJobsService = availableJobsService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAvailableJob(@RequestBody AvailableJobsEntity availableJobsEntity) {
        try{
            availableJobsService.createAvailableJob(availableJobsEntity);
            return new ResponseEntity<>("created successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAvailableJob(@RequestBody AvailableJobsEntity availableJobsEntity) {
        try{
            availableJobsService.updateAvailableJob(availableJobsEntity);
            return new ResponseEntity<>("updated successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/get")
//    public ResponseEntity<List<AvailableJobsEntity>> getAllAvailableJobs() {
//        List<AvailableJobsEntity> availableJobs = availableJobsService.getAllAvailableJobs();
//        return new ResponseEntity<>(availableJobs, HttpStatus.OK);
//    }

    @GetMapping("/get")
    public ResponseEntity<List<AvailableJobsEntity>> getAllAvailableJobs() {
        try {
            List<AvailableJobsEntity> availableJobs = availableJobsService.getAllAvailableJobs();
            return new ResponseEntity<>(availableJobs, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<AvailableJobsEntity> getAvailableJobById(@PathVariable Long id) {
        AvailableJobsEntity availableJobsEntity = availableJobsService.getAvailableJobById(id);
        return new ResponseEntity<>(availableJobsEntity, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAvailableJob(@PathVariable Long id) {
        availableJobsService.deleteAvailableJob(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
