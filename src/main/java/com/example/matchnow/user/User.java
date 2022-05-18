package com.example.matchnow.user;

import com.example.matchnow.message.Message;
import com.example.matchnow.project.Project;
import com.example.matchnow.skillStack.SkillStack;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Table(name="USERS")
@Getter @Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Long userId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name="github_link")
    private String githubLink;

    @Column(name = "blog_link")
    private String blogLink;

    private String job;

    @Column(name="last_login_at", nullable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date LastLoginAt;

    @Column(name="create_at",nullable = false,
            updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createAt;

    @OneToMany(mappedBy = "user") // user가 가진 스킬 스택
    private List<SkillStack> skillStackList = new ArrayList<>();

    // 내가 보낸 쪽지 목룍
    @OneToMany(mappedBy = "sender")
    private List<Message> sendMessageList = new ArrayList<>();

    // 받은 쪽지 목록
    @OneToMany(mappedBy = "recevier")
    private List<Message> receivedMessageList = new ArrayList<>();
}
