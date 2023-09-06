package com.example.portfolio.controller;

import com.example.portfolio.controller.dto.ProjectDto;
import com.example.portfolio.model.Project;
import com.example.portfolio.service.ProjectManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    private final ProjectManager projectManager;

    public ProjectController(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    @GetMapping(value = "/project/{projectId}")
    public ProjectDto getProject(@PathVariable String projectId) {
        return toDto(projectManager.getProject(projectId));
    }

    @GetMapping(value = "/projects")
    public List<ProjectDto> findProjects() {
        return projectManager.getAllProject().stream()
                .map(this::toDto)
                .toList();
    }

    @PostMapping(value = "/project/create")
    public ProjectDto getProject(@RequestBody ProjectDto projectDto) {
        return toDto(projectManager.createProject(projectDto));
    }

    private ProjectDto toDto(Project project) {
        return ProjectDto.from(project);
    }
}
