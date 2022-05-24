package com.example.matchnow.message;

import java.util.List;

public interface MessageRepository {

    List<Message> findAllRecv(String email);

    void sendMessage(Message message);

    void deleteMessage(Long messageId);

    void deleteMessageAll(Long userId);

    List<Message> findAllSend(String email);
}
