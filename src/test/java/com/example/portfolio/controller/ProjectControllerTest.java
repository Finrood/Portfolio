package com.example.portfolio.controller;

import com.example.portfolio.PortfolioApplication;
import com.example.portfolio.controller.dto.ProjectDto;
import com.example.portfolio.model.Project;
import com.example.portfolio.model.Technology;
import com.example.portfolio.repository.ProjectRepository;
import com.example.portfolio.utils.LocalizedString;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {PortfolioApplication.class})
@ActiveProfiles("test")
class ProjectControllerTest {
    private final List<Project> savedProjects = new ArrayList<>();
    private final ObjectMapper objectMapper;
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ProjectRepository projectRepository;

    public ProjectControllerTest() {
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @PostConstruct
    public void initialize() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();
    }

    @BeforeEach
    void setUp() {
       final Project savedP1 = projectRepository.save(new Project(
               new LocalizedString().setTranslations(
                       Map.of(
                               Locale.ENGLISH, "title1",
                               Locale.FRENCH, "titre1")
               ),
               Year.of(2000))
               .setDescription(new LocalizedString().setTranslations(
                       Map.of(
                               Locale.ENGLISH, "descren1",
                               Locale.FRENCH, "descrfr1")
               ))
               .setGithubUrl("www.gittest.com/project1")
               .setEndYear(Year.of(2001))
               .addTechnologies(Technology.JAVA, Technology.AI, Technology.SQL));

        final Project savedP2 = projectRepository.save(new Project(
                new LocalizedString().setTranslations(
                        Map.of(
                                Locale.ENGLISH, "title2",
                                Locale.FRENCH, "titre2")
                ),
                Year.of(2001))
                .setDescription(new LocalizedString().setTranslations(
                        Map.of(
                                Locale.ENGLISH, "descren2",
                                Locale.FRENCH, "descrfr2")
                ))
                .setGithubUrl("www.gittest.com/project2")
                .addTechnologies(Technology.PYTHON));

        final Project savedP3= projectRepository.save(new Project(new LocalizedString().setFallbackText("title3"), Year.of(2002))
                .setDescription(new LocalizedString().setTranslations(
                        Map.of(
                                Locale.ENGLISH, "descren3",
                                Locale.FRENCH, "descrfr3")
                ))
                .setGithubUrl("www.gittest.com/project3")
                .setEndYear(Year.of(2003))
                .addTechnologies(Technology.JAVA, Technology.AI));

        savedProjects.add(savedP1);
        savedProjects.add(savedP2);
        savedProjects.add(savedP3);
    }

    @Test
    @Transactional
    public void getProjects() throws Exception {
        final ResultActions mvcResult = mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)));

        final List<ProjectDto> projectDto = objectMapper.readValue(mvcResult.andReturn().getResponse().getContentAsString(), new TypeReference<List<ProjectDto>>() {});

        assertThat(projectDto.size()).isEqualTo(3);

        assertThat(projectDto.stream()
                .map(ProjectDto::getTitle)
                .collect(Collectors.toSet()))
                .allMatch(projectDtoTitle -> savedProjects.stream()
                        .anyMatch(project -> project.getTitle().equals(projectDtoTitle)));
    }

    @Test
    @Transactional
    public void getProjectExists() throws Exception {
        final ResultActions mvcResult = mockMvc.perform(get("/project/{projectId}", savedProjects.get(0).getId()))
                .andExpect(status().isOk());

        final ProjectDto projectDto = objectMapper.readValue(mvcResult.andReturn().getResponse().getContentAsString(), ProjectDto.class);

        assertThat(projectDto).isNotNull();

        assertThat(projectDto.getGithubUrl()).isEqualTo(savedProjects.get(0).getGithubUrl());
        assertThat(projectDto.getStartYear()).isEqualTo(savedProjects.get(0).getStartYear());
        assertThat(projectDto.getEndYear()).isEqualTo(savedProjects.get(0).getEndYear());
        assertThat(projectDto.getTechnologies()).isEqualTo(savedProjects.get(0).getTechnologies());
        assertThat(projectDto.getTitle()).isEqualTo(savedProjects.get(0).getTitle());
        assertThat(projectDto.getDescription()).isEqualTo(savedProjects.get(0).getDescription());
    }

    @Test
    @Transactional
    public void getProjectNotExists() throws Exception {
        mockMvc.perform(get("/project/{projectId}", "notAnId"))
                .andExpect(status().isNotFound());
    }
}