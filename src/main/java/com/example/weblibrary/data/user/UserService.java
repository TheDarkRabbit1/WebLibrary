package com.example.weblibrary.data.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    public Long insertUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.insertUser(user);
    }
    public User findUserByUsername(String username){
        return userDao.findByUsername(username).orElseThrow();
    }
}
