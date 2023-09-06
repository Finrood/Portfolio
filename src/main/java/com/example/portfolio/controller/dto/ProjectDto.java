package com.example.portfolio.controller.dto;

import com.example.portfolio.model.Project;
import com.example.portfolio.model.Technology;
import com.example.portfolio.utils.LocalizedString;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class ProjectDto {
    private LocalizedString title;
    private LocalizedString description;
    private List<Technology> technologies = new ArrayList<>();
    private String githubUrl;
    private Year startYear;
    private Year endYear;

    public static ProjectDto from(Project project) {
        return new ProjectDto()
                .setTitle(project.getTitle())
                .setDescription(project.getDescription())
                .setTechnologies(project.getTechnologies())
                .setGithubUrl(project.getGithubUrl())
                .setStartYear(project.getStartYear())
                .setEndYear(project.getEndYear());
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

    public Year getStartYear() {
        return startYear;
    }

    public ProjectDto setStartYear(Year startYear) {
        this.startYear = startYear;
        return this;
    }

    public Year getEndYear() {
        return endYear;
    }

    public ProjectDto setEndYear(Year endYear) {
        this.endYear = endYear;
        return this;
    }
}
