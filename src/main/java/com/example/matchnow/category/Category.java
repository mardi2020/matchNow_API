package com.example.matchnow.category;

import com.example.matchnow.project.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name="CATEGORIES")
@Builder
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue
    @Column(name="category_id")
    private Long categoryId;

    @Enumerated(EnumType.STRING)
    @Column(name="category_name", nullable = false)
    private Types categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    public Category() {}
}
