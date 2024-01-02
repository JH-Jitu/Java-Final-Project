package dev.service;

import dev.domain.ProfileEntity;
import dev.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@SuppressWarnings("ALL")
@Service
@Transactional
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List getAllProfiles() {
        return profileRepository.getAllProfiles();
    }

    public void createProfile(ProfileEntity profileEntity) {
        profileRepository.createProfile(profileEntity);
    }

    public void updateProfile(ProfileEntity profileEntity) {
        profileRepository.updateProfile(profileEntity);
    }

    public void deleteProfile(Long profileId) {
        profileRepository.deleteProfile(profileId);
    }

    public ProfileEntity getProfile(Long profileId) {
        return profileRepository.getProfile(profileId);
    }

    public ProfileEntity findByEmail(String email) {
        return profileRepository.findByEmail(email);
    }
}
