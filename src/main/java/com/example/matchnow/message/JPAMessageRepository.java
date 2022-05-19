package com.example.matchnow.message;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JPAMessageRepository implements MessageRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Message> findAll(String email) {
//        return em.createNativeQuery("SELECT * FROM matchnow.messages WHERE recevier_user_id=?", Message.class)
//                .setParameter(1, userId)
//                .getResultList();

        return em.createQuery("SELECT message FROM Message message WHERE message.recevier.email=:email", Message.class)
                .setParameter("email", email)
                .getResultList();
    }

    @Override
    public void sendMessage(Message message) {
        em.persist(message);
    }
}
