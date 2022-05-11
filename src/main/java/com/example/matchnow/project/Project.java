package com.example.matchnow.project;

import com.example.matchnow.category.Category;
import com.example.matchnow.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="PROJECTS")
@Setter @Getter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="project_id")
    private Long projectId;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // writer

    @Column(name="main_text", nullable = false)
    private String mainText;

    @Column(name = "input_image")
    private String inputImage;

    @Column(name="want_cnt", nullable = false)
    private int wantCnt;

    @Column(name="now_cnt", nullable = false)
    @ColumnDefault("1")
    private int nowPeopleCnt;

    @ManyToMany
    private List<User> userList = new ArrayList<>(); // 프로젝트에 포함된 유저들

    @Column(name="create_at", nullable = false,
            updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    private Date createAt;

    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "project")
    private List<Category> categoryList = new ArrayList<>();

    /* writer 설정 */
    public void setUser(User user) {
        this.user = user;
        user.getWrittenProjectList().add(this);
    }

    /**
     * 프로젝트에 참여한 유저 추가
    * @param user 프로젝트에 신규 참여한 유저
     */
    public void addUser(User user) {
        userList.add(user); // 현재 프로젝트 인원 정보에도 추가
        nowPeopleCnt += 1; // 인원 증가
        user.getJoinedProjectList().add(this); // 해당 유저의 projectList에도 프로젝트 추가
    }
}
