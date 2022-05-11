package com.example.matchnow.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JPAUserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private final EntityManager em;

    @Override
    public void delete(long id) { }

    @Override
    public void join(User user) {
        em.persist(user);
    }

    @Override
    public User findById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT user FROM User user", User.class)
                .getResultList();
    }

    @Override
    public List<User> findByEmail(String email) {
        return em.createQuery("SELECT user FROM User user WHERE user.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
    }
}
