package com.example.portfolio.controller;

import com.example.portfolio.controller.dto.ProfileDto;
import com.example.portfolio.model.Profile;
import com.example.portfolio.service.ProfileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
    private final ProfileManager profileManager;

    public ProfileController(ProfileManager profileManager) {
        this.profileManager = profileManager;
    }

    @GetMapping("/profile/{email}")
    public ProfileDto getProfile(@PathVariable("email") String email) {
        return toDto(profileManager.getProfile(email));
    }

    private ProfileDto toDto(Profile profile) {
        return ProfileDto.from(profile);
    }
}
