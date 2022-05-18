package com.example.matchnow.project;

import com.example.matchnow.category.Category;
import com.example.matchnow.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="PROJECTS")
@Setter @Getter
@ToString
public class Project {
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

    @Column(name="create_at", nullable = false,
            updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    private Date createAt;

    @Enumerated(EnumType.ORDINAL)
    private State state = State.RECRUITING;

    @OneToMany(mappedBy = "project")
    private List<Category> categoryList = new ArrayList<>();

    /* writer 설정 */
    public void setWriter(User user) {
        this.user = user;
    }

}
