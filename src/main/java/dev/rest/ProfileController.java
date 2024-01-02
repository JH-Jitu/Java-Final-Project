package dev.rest;

import dev.domain.ProfileEntity;
import dev.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/programmer")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/get")
    public List getAllProgrammers() {
        return profileService.getAllProfiles();
    }


    @PostMapping("/create")
    public ResponseEntity<String> createProfile(@RequestBody ProfileEntity profileEntity) {
        try {
            profileService.createProfile(profileEntity);
            return new ResponseEntity<>("Profile created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create profile.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProfile(@RequestBody ProfileEntity profileEntity) {
        try {
            profileService.updateProfile(profileEntity);
            return new ResponseEntity<>("Profile updated successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update profile.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{profileId}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long profileId) {
        try {
            profileService.deleteProfile(profileId);
            return new ResponseEntity<>("Profile deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete profile.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<?> getProfileByEmail(@PathVariable String email) {
        ProfileEntity profileEntity = profileService.findByEmail(email);
        if (profileEntity != null) {
            return new ResponseEntity<>(profileEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Profile not found.", HttpStatus.NOT_FOUND);
        }
    }
}
