package com.example.matchnow.message;

import com.example.matchnow.user.User;
import lombok.Data;


@Data
public class SendMessageDTO {

    private String sender;

    private String receiver;

    private String title;

    private String mainText;

    private String date;

    public Message toEntity(User sender, User recevier) {
        Message entity = Message.builder()
                .sender(sender)
                .recevier(recevier)
                .title(title)
                .mainText(mainText)
                .sendDate(date)
                .build();

        sender.getSendMessageList().add(entity);
        recevier.getReceivedMessageList().add(entity);

        return entity;
    }

    public SendMessageDTO(Message entity) {
        this.sender = entity.getSender().getUsername();
        this.receiver = entity.getRecevier().getUsername();
        this.title = entity.getTitle();
        this.mainText = entity.getMainText();
        this.date = entity.getSendDate();
    }
}
