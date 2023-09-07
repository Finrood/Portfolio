package com.example.portfolio.model;

import com.example.portfolio.model.support.LocalizedStringJpaConverter;
import com.example.portfolio.model.support.TechnologyCollectionJpaConverter;
import com.example.portfolio.utils.LocalizedString;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.*;

@Entity
public class Project {
    @Id
    private String id;
    @Column(nullable = false)
    @Convert(converter = LocalizedStringJpaConverter.class)
    private LocalizedString title;
    @Column(columnDefinition = "TEXT")
    @Convert(converter = LocalizedStringJpaConverter.class)
    private LocalizedString description;
    @Convert(converter = TechnologyCollectionJpaConverter.class)
    private final List<Technology> technologies = new ArrayList<>();
    private String githubUrl;
    private String startYear;
    private String endYear;


    protected Project() {
        // For JPA only
    }

    public Project(LocalizedString title, String startYear) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.startYear = startYear;
    }

    public String getId() {
        return id;
    }

    public LocalizedString getTitle() {
        return title;
    }

    public Project setTitle(LocalizedString title) {
        this.title = title;
        return this;
    }

    public LocalizedString getDescription() {
        return description;
    }

    public Project setDescription(LocalizedString description) {
        this.description = description;
        return this;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public Project addTechnologies(Technology... technologies) {
        this.technologies.addAll(Arrays.asList(technologies));
        return this;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public Project setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
        return this;
    }

    public String getStartYear() {
        return startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public Project setEndYear(String endYear) {
        this.endYear = endYear;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", title=" + title +
                ", description=" + description +
                ", technologies=" + technologies +
                ", githubUrl='" + githubUrl + '\'' +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                '}';
    }
}
