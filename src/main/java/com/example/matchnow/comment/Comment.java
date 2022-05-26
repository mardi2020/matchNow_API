package com.example.matchnow.comment;

import com.example.matchnow.BaseTimeEntity;
import com.example.matchnow.project.Project;
import com.example.matchnow.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name="comment_id")
    private Long commentId;

    @Column(name="comment_text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({
            "title", "user", "mainText", "inputImage", "wantCnt",
            "nowPeopleCnt", "createdDate", "modifiedDate", "state", "categoryList",
            "comments", "hibernateLazyInitializer"
    })
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="comment_user_id", insertable = false, updatable = false)
    @JsonBackReference
    private User user;

    @JsonBackReference
    @Column(name="is_deleted")
    private boolean isDeleted;
}
