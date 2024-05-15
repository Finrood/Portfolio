package com.example.portfolio.controller;

import com.example.portfolio.PortfolioApplication;
import com.example.portfolio.model.Profile;
import com.example.portfolio.repository.ProfileRepository;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {PortfolioApplication.class})
@ActiveProfiles("test")
class ProfileControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ProfileRepository profileRepository;

    @PostConstruct
    public void initialize() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();
    }

    @BeforeEach
    void setUp() {
        profileRepository.saveAll(List.of(
                new Profile("John", "Doe", LocalDate.of(2000, 6, 1), "john.doe@test.com"),
                new Profile("Mark", "Zuck", LocalDate.of(2000, 6, 1), "mark.zuck@test.com")
        ));
    }

    @Test
    @Transactional
    public void getProfileExists() throws Exception {
        mockMvc.perform(get("/profile/{email}", "john.doe@test.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john.doe@test.com"))
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Doe"))
                .andExpect(jsonPath("$.birthdate").value("2000-06-01"));

    }

    @Test
    @Transactional
    public void getProfileNotExists() throws Exception {
        mockMvc.perform(get("/profile/{email}", "missing.person@test.com"))
                .andExpect(status().isNotFound());
    }
}