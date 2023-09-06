package com.example.portfolio.repository;

import com.example.portfolio.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository  extends CrudRepository<Project, String> {
    @Override
    List<Project> findAll();
}
