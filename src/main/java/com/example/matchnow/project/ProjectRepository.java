package com.example.matchnow.project;

import com.example.matchnow.user.User;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {

    List<Project> findById(Long id);

    void updateProjectPost(Project project);

    void uploadProjectPost(Project project);

    void deleteProjectPost(Long id);

    List<Project> findAll();

    void changePostState(Long id, int state); // 마감, 모집, 취소됨

    List<Project> detailProjectPost(Long id);

    void addTeamMember(List<User> members);
}
