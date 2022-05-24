package com.example.matchnow.project;


import lombok.Data;

import java.util.Date;


@Data
public class PostedProjectDTO {

    private Long projectId;

    private String title;

    private Date createAt;

    private State state;

    private String writer;
}
