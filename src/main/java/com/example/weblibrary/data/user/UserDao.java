package com.example.weblibrary.data.user;

import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public interface UserDao {
    Optional<User> findByUsername(String username);
    Long insertUser(User user);

}
