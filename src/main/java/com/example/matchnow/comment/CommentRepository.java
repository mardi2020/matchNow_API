package com.example.matchnow.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value="select c from Comment c where c.project.projectId=?1")
    List<Comment> findAllByProject(Long ProjectId);

    @Modifying
    @Query(value = "INSERT INTO matchnow.comments(comment_text, project_project_id, comment_user_id, created_date) VALUES(?1, ?2, ?3, ?4)"
            , nativeQuery = true)
    void saveIn(String text, Long projectId, Long userId, LocalDateTime now);
}
