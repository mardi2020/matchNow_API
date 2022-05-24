package com.example.matchnow.message;

import com.example.matchnow.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Entity
@Table(name="MESSAGES")
@Setter @Getter
public class Message {

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

    @Column(nullable = false, name="send_date",
            insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    private String sendDate;

    private boolean recevierDelete;

    private boolean senderDelete;

    public void setSenderAndRecevier(User send, User to){
        sender = send;
        send.getSendMessageList().add(this);
        recevier = to;
        to.getReceivedMessageList().add(this);
    }
}
