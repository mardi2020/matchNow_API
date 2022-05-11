package com.example.matchnow.user;


import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
@Getter
public class Email {

    private String email;

    private final String regexPattern = "^(.+)@(\\\\S+)$";

    public Email(String email) {
        this.email = email;
    }

    public Email() { }

    public boolean checkValidEmail(String email) {
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }
}
