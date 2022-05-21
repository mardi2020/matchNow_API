package com.example.matchnow.message;

import java.util.List;

public interface MessageRepository {

    List<Message> findAll(String email);

    void sendMessage(Message message);

    void deleteMessage(Long messageId);

    void deleteMessageAll(Long userId);

}
