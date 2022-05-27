package com.example.matchnow.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value="SELECT message FROM Message message WHERE message.recevier.email=?1")
    List<Message> findAllRecv(String email);

    @Query(value="SELECT message FROM Message message WHERE message.sender.email=?1")
    List<Message> findAllSend(String email);

    @Modifying
    @Query(value="DELETE FROM matchnow.messages WHERE matchnow.messages.message_id=?1", nativeQuery = true)
    void deleteMessage(Long messageId);

}
