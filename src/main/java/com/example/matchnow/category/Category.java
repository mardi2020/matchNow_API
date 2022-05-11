package com.example.matchnow.category;

import com.example.matchnow.project.Project;

import javax.persistence.*;

@Entity
@Table(name="CATEGORIES")
public class Category {

    @Id
    @GeneratedValue
    @Column(name="category_id")
    private Long categoryId;

    @Column(name="category_name")
    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;
}
