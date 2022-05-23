package com.example.matchnow.message;

import lombok.Data;

import java.util.Date;

@Data
public class ReceivedMessageDTO {

    private Long messageId;

    private String title;

    private String mainText;

    private String sender;

    private String sendDate;
}
