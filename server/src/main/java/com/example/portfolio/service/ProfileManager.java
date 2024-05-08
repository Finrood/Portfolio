package com.example.portfolio.service;

import com.example.portfolio.controller.support.ResourceNotFoundException;
import com.example.portfolio.model.Profile;
import com.example.portfolio.repository.ProfileRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileManager {
    private final ProfileRepository profileRepository;

    public ProfileManager(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "profiles", key = "#email")
    public Profile getProfile(String email) {
        return profileRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("No profile with email [%s] was found", email)));
    }
}
