package com.example.matchnow.user;


import java.util.List;

public interface UserRepository {

    void delete(long id);

    void join(User user);

    User findById(long id);

    List<User> findAll();

    List<User> findByEmail(String email);

    void updateLoginDate(String email, String date);
}
