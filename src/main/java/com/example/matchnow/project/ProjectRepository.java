package com.example.matchnow.project;

import java.util.Optional;

public interface ProjectRepository {

    Optional<Project> findById(long id);

    void updateProject(Project project);

    long postProject(Project project);
}
