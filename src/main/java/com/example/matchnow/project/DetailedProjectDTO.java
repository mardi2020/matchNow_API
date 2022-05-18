package com.example.matchnow.project;

import com.example.matchnow.user.User;
import lombok.Data;


@Data
public class DetailedProjectDTO {

    private Long projectId;

    private String title;

    private String mainText;

    private String writer;

    private String inputImage;

    private int wantCnt;

    private int nowPeopleCnt;

    private State state;


    public void setUser(User source){
        writer = source.getUsername();
    }
}
