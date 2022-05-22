package com.example.matchnow.skillStack;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class JPAStackRepository implements StackRepository {

    private final EntityManager em;

    @Override
    public List<SkillStack> findById(long id) {
        return em.createQuery("SELECT stacks FROM SkillStack stacks WHERE stacks.skillStackId=:id", SkillStack.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<SkillStack> findAll(String email) {
        return em.createQuery("SELECT stacks FROM SkillStack stacks WHERE stacks.user.email=:email", SkillStack.class)
                .setParameter("email", email)
                .getResultList();
    }

    @Override
    public List<SkillStack> findByName(String name) {
        return em.createQuery("SELECT stacks FROM SkillStack stacks WHERE stacks.stackName=:name", SkillStack.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public void addMySkill(String stackName, Long userId) {
        em.createNativeQuery("INSERT INTO matchnow.skill_stacks(stack_name, user_skill_id) VALUES(?, ?)")
                .setParameter(1, stackName)
                .setParameter(2, userId)
                .executeUpdate();

    }
}
