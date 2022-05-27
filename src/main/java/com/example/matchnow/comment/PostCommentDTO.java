package com.example.matchnow.comment;

import com.example.matchnow.project.Project;
import com.example.matchnow.user.User;
import lombok.Data;

@Data
public class PostCommentDTO {

    private String text;

    private Long projectId;

}
