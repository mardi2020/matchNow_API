package com.example.matchnow.skillStack;

import com.example.matchnow.user.User;

import javax.persistence.*;

@Entity
@Table(name="SKILL_STACKS")
public class SkillStack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="skill_stack_id")
    private Long skillStackId;

    @Column(name="stack_name", unique = true, nullable = false)
    private String stackName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stack_id")
    private User user; //user
}
