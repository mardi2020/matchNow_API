package com.example.matchnow.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JPAProjectRepository implements ProjectRepository{

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Project> findById(Long id) {
        return em.createQuery("SELECT project FROM Project project WHERE project.projectId =:id", Project.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void updateProjectPost(Project project) {

    }

    @Override
    public void uploadProjectPost(Project project) {
        em.persist(project);
    }

    @Override
    public void deleteProjectPost(Long id) {

    }

    @Override
    public List<Project> findAll() {
        return em.createQuery("SELECT project FROM Project project", Project.class)
                .getResultList();
    }

    @Override
    public void changePostState(Long id) {

    }
}
