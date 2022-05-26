package com.example.matchnow.project;

import com.example.matchnow.BaseTimeEntity;
import com.example.matchnow.category.Category;
import com.example.matchnow.comment.Comment;
import com.example.matchnow.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="PROJECTS")
@Getter
@AllArgsConstructor
@Builder
public class Project extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="project_id")
    private Long projectId;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // writer

    @Column(name="main_text", nullable = false)
    private String mainText;

    @Column(name = "input_image")
    private String inputImage;

    @Column(name="want_cnt", nullable = false)
    private int wantCnt;

    @Column(name="now_cnt",
            columnDefinition = "INT DEFAULT 1"
    )
    private int nowPeopleCnt;

    @Enumerated(EnumType.ORDINAL)
    private State state;

    @OneToMany(mappedBy = "project")
    private List<Category> categoryList;

    @OneToMany(mappedBy = "project")
    private List<Comment> comments;

    public Project() {}
}
