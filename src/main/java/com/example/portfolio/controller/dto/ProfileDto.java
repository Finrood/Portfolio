package com.example.portfolio.controller.dto;

import com.example.portfolio.model.Profile;
import com.example.portfolio.utils.LocalizedString;

import java.time.LocalDate;

public class ProfileDto {
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String email;
    private String phoneNumber;
    private LocalizedString biography;
    private String photoKey;
    public static ProfileDto from(Profile profile) {
        return new ProfileDto()
                .setFirstname(profile.getFirstname())
                .setLastname(profile.getLastname())
                .setBirthdate(profile.getBirthdate())
                .setEmail(profile.getEmail())
                .setPhoneNumber(profile.getPhoneNumber())
                .setBiography(profile.getBiography())
                .setPhotoKey(profile.getPhotoKey());

    }

    public String getFirstname() {
        return firstname;
    }

    public ProfileDto setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public ProfileDto setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public ProfileDto setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProfileDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ProfileDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public LocalizedString getBiography() {
        return biography;
    }

    public ProfileDto setBiography(LocalizedString biography) {
        this.biography = biography;
        return this;
    }

    public String getPhotoKey() {
        return photoKey;
    }

    public ProfileDto setPhotoKey(String photoKey) {
        this.photoKey = photoKey;
        return this;
    }
}
