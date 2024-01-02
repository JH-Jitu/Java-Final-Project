package dev.rest;

import dev.domain.AdminEntity;
import dev.service.AdminEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@SuppressWarnings("rawtypes")
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminEntityService adminEntityService;

    @Autowired
    public AdminController(AdminEntityService adminEntityService) {
        this.adminEntityService = adminEntityService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllAdminEntities() {
        try {
            List adminEntities = adminEntityService.getAllAdminEntities();
            return new ResponseEntity<>(adminEntities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve admin entities.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAdminEntity(@RequestBody AdminEntity adminEntity) {
        try {
            adminEntityService.createAdminEntity(adminEntity);
            return new ResponseEntity<>("Admin entity created successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create admin entity.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAdminEntity(@RequestBody AdminEntity adminEntity) {
        try {
            adminEntityService.updateAdminEntity(adminEntity);
            return new ResponseEntity<>("Admin entity updated successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update admin entity.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{adminEntityId}")
    public ResponseEntity<String> deleteAdminEntity(@PathVariable Long adminEntityId) {
        try {
            adminEntityService.deleteAdminEntity(adminEntityId);
            return new ResponseEntity<>("Admin entity deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete admin entity.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        try {
            AdminEntity adminEntity = adminEntityService.findByEmail(email);
            if (adminEntity != null) {
                return new ResponseEntity<>(adminEntity, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Admin entity not found for the given email.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to find admin entity by email.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
