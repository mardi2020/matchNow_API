package com.example.matchnow.user;

import com.example.matchnow.BaseTime;
import com.example.matchnow.comment.Comment;
import com.example.matchnow.message.Message;
import com.example.matchnow.skillStack.SkillStack;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USERS")
@NoArgsConstructor
@Getter
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(name="github_link")
    private String githubLink;

    @Column(name = "blog_link")
    private String blogLink;

    private String job;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @JsonBackReference
    private Role role;

    @OneToMany(mappedBy = "user") // user가 가진 스킬 스택
    private List<SkillStack> skillStackList = new ArrayList<>();

    // 내가 보낸 쪽지 목룍
    @OneToMany(mappedBy = "sender")
    @JsonIgnoreProperties({
            "sender"
    })
    private List<Message> sendMessageList = new ArrayList<>();

    // 받은 쪽지 목록
    @OneToMany(mappedBy = "recevier")
    @JsonIgnoreProperties({
            "recevier"
    })
    private List<Message> receivedMessageList = new ArrayList<>();

    @JsonIgnoreProperties({
            "deleted", "modifiedDate"
    })
    @OneToMany
    @JoinColumn(name="comment_user_id")
    private List<Comment> comments = new ArrayList<>();


    @Builder
    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Builder
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
