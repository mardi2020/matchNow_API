package com.example.matchnow.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping(value="/comment/{id}")
    public ResponseEntity<?> findAllComment(@PathVariable Long id) {
        try {
            List<Comment> allComment = commentService.findAllComment(id);
            return new ResponseEntity<>(allComment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("댓글을 불러오지 못했습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/comment/{id}")
    public ResponseEntity<?> postComment(PostCommentDTO postCommentDTO) {
        try {
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }
}
