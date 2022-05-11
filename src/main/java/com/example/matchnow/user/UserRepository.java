package com.example.matchnow.user;


import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void delete(long id);

    void join(User user);

    Optional<User> findById(long id);

    List<User> findAll();

    Optional<User> findByEmail(Email email);
}
