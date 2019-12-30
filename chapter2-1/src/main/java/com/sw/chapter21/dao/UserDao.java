package com.sw.chapter21.dao;

import com.sw.chapter21.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component("userRepository")
public interface UserDao extends JpaRepository<User, Long> {
    User findByName(String name);
}
