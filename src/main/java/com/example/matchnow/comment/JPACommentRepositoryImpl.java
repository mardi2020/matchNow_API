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

    }

    @Override
    public void deleteComment(Long commentId) {

    }

    @Override
    public List<Comment> findAll(Long projectId) {
        return em.createQuery("SELECT comment FROM Comment comment WHERE comment.project.projectId=:projectId", Comment.class)
                .setParameter("projectId", projectId)
                .getResultList();
    }
}
