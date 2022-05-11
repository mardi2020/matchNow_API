package com.example.matchnow.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JPAUserRepositoryImpl implements UserRepository{


    @Override
    public void delete(long id) {

    }

    @Override
    public void join(User user) {

    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return Optional.empty();
    }
}
