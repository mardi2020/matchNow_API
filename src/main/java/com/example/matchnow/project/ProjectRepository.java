package com.example.matchnow.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Modifying
    @Query(value="update matchnow.projects set is_deleted=true where project_id=?1", nativeQuery = true)
    void deleteProjectPost(Long id);

    @Modifying
    @Query(value="update matchnow.projects set state=?2 where project_id=?1", nativeQuery = true)
    void changePostState(Long id, int state); // 마감, 모집, 취소됨

    @Query(value="SELECT project FROM Project project WHERE project.projectId=?1")
    List<Project> detailProjectPost(Long id);

//    void addTeamMember(List<User> members);
}
