package com.example.matchnow.project;

import com.example.matchnow.user.User;
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
        em.createNativeQuery("UPDATE matchnow.projects SET title=?, main_text=?, input_image=? WHERE project_id=?")
                .setParameter(1, project.getTitle())
                .setParameter(2, project.getMainText())
                .setParameter(3, project.getInputImage())
                .setParameter(4, project.getProjectId())
                .executeUpdate();
    }

    @Override
    public void uploadProjectPost(Project project) { ;
        em.persist(project);
    }

    @Override
    public void deleteProjectPost(Long id) {
        em.createNativeQuery("DELETE FROM matchnow.projects WHERE project_id=?")
                .setParameter(1, id)
                .executeUpdate();
    }

    @Override
    public List<Project> findAll() {
        return em.createQuery("SELECT project FROM Project project", Project.class)
                .getResultList();
    }

    @Override
    public void changePostState(Long id, int state) {
        em.createNativeQuery("UPDATE matchnow.projects SET state=? WHERE project_id=?")
                .setParameter(1, state)
                .setParameter(2, id)
                .executeUpdate();
    }

    @Override
    public List<Project> detailProjectPost(Long id) {
        return em.createQuery("SELECT project FROM Project project WHERE project.projectId=:id", Project.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void addTeamMember(List<User> members) {

    }
}
