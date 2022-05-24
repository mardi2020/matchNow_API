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
    public List<Message> findAllRecv(String email) {

        return em.createQuery("SELECT message FROM Message message WHERE message.recevier.email=:email", Message.class)
                .setParameter("email", email)
                .getResultList();
    }

    @Override
    public List<Message> findAllSend(String email){
        return em.createQuery("SELECT message FROM Message message WHERE message.sender.email=:email", Message.class)
                .setParameter("email", email)
                .getResultList();
    }

    @Override
    public void sendMessage(Message message) {
        em.persist(message);
    }

    @Override
    public void deleteMessage(Long messageId) {
//        em.createQuery("DELETE FROM Message message WHERE message.messageId=:messageId")
//                .setParameter("messageId", messageId)
//                .executeUpdate();
        em.createNativeQuery("DELETE FROM matchnow.messages WHERE matchnow.messages.message_id=?", Message.class)
                .setParameter(1, messageId)
                .executeUpdate();
    }

    @Override
    public void deleteMessageAll(Long userId) {
//        em.createQuery("DELETE FROM Message message WHERE message.recevier.email=:email")
//                .setParameter("email", email)
//                .executeUpdate();
        em.createNativeQuery("DELETE FROM matchnow.messages WHERE matchnow.messages.recevier_user_id=?")
                .setParameter(1, userId)
                .executeUpdate();

    }
}
