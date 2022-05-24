package com.example.matchnow.comment;


import com.example.matchnow.project.Project;
import com.example.matchnow.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;



@Data
public class ResponseCommentDTO {
    private Long commentId;

    private String text;

    @JsonIgnoreProperties({
            "title", "user", "mainText", "inputImage", "wantCnt",
            "nowPeopleCnt", "createAt", "state", "categoryList",
            "comments", "hibernateLazyInitializer"
    })
    private Project project;

    private String date;

    @JsonIgnoreProperties({"email", "githubLink", "blogLink", "job",
            "lastLoginAt", "createAt", "skillStackList", "comments", "hibernateLazyInitializer"
    })
    private User user;

}
