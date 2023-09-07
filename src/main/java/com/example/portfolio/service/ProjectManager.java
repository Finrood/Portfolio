package com.example.portfolio.service;

import com.example.portfolio.controller.dto.ProjectDto;
import com.example.portfolio.controller.support.ResourceNotFoundException;
import com.example.portfolio.model.Project;
import com.example.portfolio.model.Technology;
import com.example.portfolio.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectManager {
    private  final ProjectRepository projectRepository;

    public ProjectManager(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional(readOnly = true)
    public Project getProject(String projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Project with id [%s] not found", projectId)));
    }

    @Transactional(readOnly = true)
    public List<Project> getAllProject() {
        return projectRepository.findAll();
    }

    @Transactional
    public Project createProject(ProjectDto projectDto) {
        return projectRepository.save(new Project(projectDto.getTitle(), projectDto.getStartYear())
                .setDescription(projectDto.getDescription())
                .setGithubUrl(projectDto.getGithubUrl())
                .setEndYear(projectDto.getEndYear())
                .addTechnologies(projectDto.getTechnologies().toArray(Technology[]::new)));
    }
}
