package com.example.matchnow.user;

import lombok.Data;

@Data
public class UserJoinDTO {

    private String email;

    private String password;

    private String username;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .username(username)
                .build();
    }
}
