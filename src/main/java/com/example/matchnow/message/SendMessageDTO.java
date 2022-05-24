package com.example.matchnow.message;

import lombok.Data;


@Data
public class SendMessageDTO {

    private String sender;

    private String receiver;

    private String title;

    private String mainText;

    private String date;
}
