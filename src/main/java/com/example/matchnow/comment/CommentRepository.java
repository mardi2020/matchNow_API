package com.example.matchnow.comment;

import java.util.List;

public interface CommentRepository {

    void postComment(String text, Long projectId, Long userId);

    void deleteComment(Long commentId);

    List<Comment> findAll(Long projectId);
}
