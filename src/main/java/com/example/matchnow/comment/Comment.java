package com.example.matchnow.comment;

import com.example.matchnow.project.Project;
import com.example.matchnow.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Comment {
    @Id
    @GeneratedValue
    @Column(name="comment_id")
    private Long commentId;

    @Column(name="comment_text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({
            "title", "user", "mainText", "inputImage", "wantCnt",
            "nowPeopleCnt", "createAt", "state", "categoryList",
            "comments", "hibernateLazyInitializer"
    })
    private Project project;

    @Column(name="create_at", nullable = false,
            updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    private String date;


    @JsonIgnoreProperties({"email", "githubLink", "blogLink", "job",
            "lastLoginAt", "createAt", "skillStackList", "comments", "hibernateLazyInitializer"
    })
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="comment_user_id", insertable = false, updatable = false)
    private User user;

    @JsonBackReference
    private boolean isDeleted;
}
