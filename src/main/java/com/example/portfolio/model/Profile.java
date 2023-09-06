package com.example.portfolio.model;

import com.example.portfolio.model.support.LocalizedStringJpaConverter;
import com.example.portfolio.utils.LocalizedString;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Profile {
    @Id
    private String id;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false)
    private LocalDate birthdate;
    @Column(nullable = false, unique = true)
    private String email;
    private String phoneNumber;
    @Column(columnDefinition = "TEXT")
    @Convert(converter = LocalizedStringJpaConverter.class)
    private LocalizedString biography;
    @Lob
    @Column
    private String photoKey;

    protected Profile() {
        // For JPA
    }

    public Profile(String firstname, String lastname, LocalDate birthdate, String email) {
        this.id = UUID.randomUUID().toString();
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalizedString getBiography() {
        return biography;
    }

    public void setBiography(LocalizedString biography) {
        this.biography = biography;
    }

    public String getPhotoKey() {
        return photoKey;
    }

    public void setPhotoKey(String photoKey) {
        this.photoKey = photoKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Profile project = (Profile) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthdate=" + birthdate +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", biography=" + biography +
                ", photoKey='" + photoKey + '\'' +
                '}';
    }
}
