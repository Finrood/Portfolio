package com.example.portfolio.repository;

import com.example.portfolio.model.Profile;
import com.example.portfolio.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, String> {
    Optional<Profile> findByEmail(String email);
}
