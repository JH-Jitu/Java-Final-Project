package dev.rest;

import dev.domain.CompanyEntity;
import dev.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/get")
    public List getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCompany(@RequestBody CompanyEntity companyEntity) {
        try {
            companyService.createCompany(companyEntity);
            return new ResponseEntity<>("Company created successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create company.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCompany(@RequestBody CompanyEntity companyEntity) {
        try {
            companyService.updateCompany(companyEntity);
            return new ResponseEntity<>("Company updated successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update company.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{companyId}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long companyId) {
        try {
            companyService.deleteCompany(companyId);
            return new ResponseEntity<>("Company deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete company.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/{companyId}")
//    public ResponseEntity<CompanyEntity> getCompany(@PathVariable Long companyId) {
//        CompanyEntity companyEntity = companyService.getCompany(companyId);
//        if (companyEntity != null) {
//            return new ResponseEntity<>(companyEntity, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/get/{email}")
    public ResponseEntity<CompanyEntity> getCompanyByEmail(@PathVariable String email) {
        CompanyEntity companyEntity = companyService.findByEmail(email);
        if (companyEntity != null) {
            return new ResponseEntity<>(companyEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
