package com.example.matchnow.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JPACommentRepositoryImpl implements CommentRepository {

    private final EntityManager em;

    @Override
    public void postComment(String text, Long projectId, Long userId) {
        em.createNativeQuery("INSERT INTO matchnow.comment(comment_text, project_project_id, comment_user_id) VALUES(?, ?, ?)")
        .setParameter(1, text)
        .setParameter(2, projectId)
        .setParameter(3, userId)
        .executeUpdate();
    }

    @Override
    public void deleteComment(Long commentId) {
        em.createNativeQuery("UPDATE matchnow.comment SET matchnow.comment.is_deleted=true WHERE comment_id=?")
                .setParameter(1, commentId)
                .executeUpdate();
    }

    @Override
    public List<Comment> findAll(Long projectId) {
        return em.createQuery("SELECT comment FROM Comment comment WHERE comment.project.projectId=:projectId and comment.isDeleted=FALSE", Comment.class)
                .setParameter("projectId", projectId)
                .getResultList();
    }
}
