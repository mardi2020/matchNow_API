package com.example.matchnow.message;


import com.example.matchnow.user.User;
import com.example.matchnow.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    @Transactional
    public List<String> sendMessage(SendMessageDTO sendMessageDTO, String myEmail) {
        Message message = new Message();
        User from = userRepository.findByEmail(myEmail).get(0);
        User to = userRepository.findByEmail(sendMessageDTO.getReceiver()).get(0);

        message.setSenderAndRecevier(from, to);
        message.setMainText(sendMessageDTO.getMainText());
        message.setTitle(sendMessageDTO.getTitle());

        messageRepository.sendMessage(message);

        return Arrays.asList(from.getUsername(), to.getUsername());
    }

    public List<ReceivedMessageDTO> findAllRecv(String email) {
        List<Message> messages = messageRepository.findAllRecv(email);

        List<ReceivedMessageDTO> receivedMessageDTOList = new ArrayList<>();
        for (Message message : messages) {
            ReceivedMessageDTO receivedMessageDTO = new ReceivedMessageDTO();
            receivedMessageDTO.setMessageId(message.getMessageId());
            receivedMessageDTO.setTitle(message.getTitle());
            receivedMessageDTO.setMainText(message.getMainText());
            receivedMessageDTO.setSender(message.getSender().getUsername());
            receivedMessageDTO.setSendDate(message.getSendDate());
            receivedMessageDTOList.add(receivedMessageDTO);
        }
        return receivedMessageDTOList;
    }

    public List<SendMessageDTO> findAllSend(String email) {
        List<Message> messages = messageRepository.findAllSend(email);

        List<SendMessageDTO> sendList = new ArrayList<>();
        for (Message message : messages) {
            System.out.println("message = " + message.getTitle());
            SendMessageDTO sendMessageDTO = new SendMessageDTO();

            sendMessageDTO.setSender(message.getSender().getUsername());
            sendMessageDTO.setReceiver(message.getRecevier().getUsername());
            sendMessageDTO.setTitle(message.getTitle());
            sendMessageDTO.setMainText(message.getMainText());
            sendMessageDTO.setDate(message.getSendDate());
            sendList.add(sendMessageDTO);
        }
        return sendList;
    }

    @Transactional
    public void deleteMessageAll(String email){
        Long userId = userRepository.findByEmail(email).get(0).getUserId();
        messageRepository.deleteMessageAll(userId);
    }

    @Transactional
    public void deleteMessage(Long messageId) {
        messageRepository.deleteMessage(messageId);
    }
}
