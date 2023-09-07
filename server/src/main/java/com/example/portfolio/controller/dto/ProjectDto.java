package com.example.portfolio.controller.dto;

import com.example.portfolio.model.Project;
import com.example.portfolio.model.Technology;
import com.example.portfolio.utils.LocalizedString;

import java.util.ArrayList;
import java.util.List;

public class ProjectDto {
    private String id;
    private LocalizedString title;
    private LocalizedString description;
    private List<Technology> technologies = new ArrayList<>();
    private String githubUrl;
    private String startYear;
    private String endYear;

    public static ProjectDto from(Project project) {
        return new ProjectDto()
                .setId(project.getId())
                .setTitle(project.getTitle())
                .setDescription(project.getDescription())
                .setTechnologies(project.getTechnologies())
                .setGithubUrl(project.getGithubUrl())
                .setStartYear(project.getStartYear())
                .setEndYear(project.getEndYear());
    }

    public String getId() {
        return id;
    }

    public ProjectDto setId(String id) {
        this.id = id;
        return this;
    }

    public LocalizedString getTitle() {
        return title;
    }

    public ProjectDto setTitle(LocalizedString title) {
        this.title = title;
        return this;
    }

    public LocalizedString getDescription() {
        return description;
    }

    public ProjectDto setDescription(LocalizedString description) {
        this.description = description;
        return this;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public ProjectDto setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
        return this;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public ProjectDto setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
        return this;
    }

    public String getStartYear() {
        return startYear;
    }

    public ProjectDto setStartYear(String startYear) {
        this.startYear = startYear;
        return this;
    }

    public String getEndYear() {
        return endYear;
    }

    public ProjectDto setEndYear(String endYear) {
        this.endYear = endYear;
        return this;
    }
}
