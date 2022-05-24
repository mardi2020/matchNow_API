package com.example.matchnow.comment;

import com.example.matchnow.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    public List<Comment> findAllComment(Long ProjectId) {
        return commentRepository.findAll(ProjectId);
    }

    public void postComment(PostCommentDTO postCommentDTO, String email) {
        Long userId = userRepository.findByEmail(email).get(0).getUserId();
        commentRepository.postComment(postCommentDTO.getText(), postCommentDTO.getProjectId(), userId);
    }
}
