package com.example.matchnow.project;


import lombok.Data;

import java.time.LocalDateTime;


@Data
public class PostedProjectDTO {

    private Long projectId;

    private String title;

    private LocalDateTime createAt;

    private State state;

    private String writer;

    public PostedProjectDTO(Project entity) {
        this.projectId = entity.getProjectId();
        this.title = entity.getTitle();
        this.createAt = entity.getCreatedDate();
        this.state = entity.getState();
        this.writer = entity.getUser().getUsername();
    }
}
