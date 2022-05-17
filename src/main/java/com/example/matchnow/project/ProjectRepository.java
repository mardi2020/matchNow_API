package com.example.matchnow.project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {

    List<Project> findById(Long id);

    void updateProjectPost(Project project);

    void uploadProjectPost(Project project);

    void deleteProjectPost(Long id);

    List<Project> findAll();

    void changePostState(Long id); // 마감, 모집, 취소됨

    List<Project> detailProjectPost(Long id);
}
