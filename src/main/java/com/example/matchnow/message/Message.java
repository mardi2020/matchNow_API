package com.example.matchnow.message;

import com.example.matchnow.user.User;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Entity
@Table(name="MESSAGES")
public class Message {

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Id
    @Column(name="message_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long messageId;

    @Column(nullable = false)
    private String title;

    @Column(name="main_text", nullable = false)
    private String mainText;

    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    private User recevier;

    @Column(nullable = false, name="send_date")
    private String sendDate;

    public void setSenderAndRecevier(User send, User to){
        sender = send;
        send.getSendMessageList().add(this);
        recevier = to;
        to.getReceivedMessageList().add(this);
        sendDate = format.format(System.currentTimeMillis());
    }
}
