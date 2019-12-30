package com.sw.chapter21.service;

import com.sw.chapter21.dao.UserDao;
import com.sw.chapter21.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userRepository;

    public User findUserByName(String name) {
        return userRepository.findByName(name);
    }

    public void insertDB(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}
