package com.example.matchnow.comment;

import com.example.matchnow.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    public List<ResponseCommentDTO> findAllComment(Long ProjectId) {
        List<Comment> comments = commentRepository.findAllByProject(ProjectId);

        List<ResponseCommentDTO> responseCommentDTOS = new ArrayList<>();

        for (Comment comment : comments) {
            responseCommentDTOS.add(new ResponseCommentDTO(comment));
        }
        return responseCommentDTOS;
    }

    @Transactional
    public void postComment(PostCommentDTO postCommentDTO, String email) {
        Long userId = userRepository.findByEmail(email).get(0).getUserId();
        commentRepository.saveIn(
                postCommentDTO.getText(),
                postCommentDTO.getProjectId(),
                userId,
                LocalDateTime.now());
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).get();
        commentRepository.delete(comment);
    }
}
