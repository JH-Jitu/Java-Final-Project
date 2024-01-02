package dev.rest;

import dev.domain.RecruiterEntity;
import dev.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/recruiter")
public class RecruiterController {

    private final RecruiterService recruiterService;

    @Autowired
    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    @GetMapping("/get")
    public List getAllRecruiters() {
        return recruiterService.getAllRecruiters();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createRecruiter(@RequestBody RecruiterEntity recruiterEntity) {
        try {
            recruiterService.createRecruiter(recruiterEntity);
            return new ResponseEntity<>("Recruiter created successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create recruiter.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateRecruiter(@RequestBody RecruiterEntity recruiterEntity) {
        try {
            recruiterService.updateRecruiter(recruiterEntity);
            return new ResponseEntity<>("Recruiter updated successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update recruiter.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{recruiterId}")
    public ResponseEntity<String> deleteRecruiter(@PathVariable Long recruiterId) {
        try {
            recruiterService.deleteRecruiter(recruiterId);
            return new ResponseEntity<>("Recruiter deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete recruiter.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/get/{recruiterId}")
//    public ResponseEntity<RecruiterEntity> getRecruiter(@PathVariable Long recruiterId) {
//        try {
//            RecruiterEntity recruiter = recruiterService.getRecruiter(recruiterId);
//            return new ResponseEntity<>(recruiter, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/get/{email}")
    public ResponseEntity<RecruiterEntity> findByEmail(@PathVariable String email) {
        try {
            RecruiterEntity recruiter = recruiterService.findByEmail(email);
            if (recruiter != null) {
                return new ResponseEntity<>(recruiter, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
