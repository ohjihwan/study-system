package com.study.service;

import com.study.mapper.UserMapper;
import com.study.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public boolean registerUser(User user) {
        if (userMapper.existsByUsername(user.getUsername()) || 
            userMapper.existsByEmail(user.getEmail())) {
            return false;
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insertUser(user);
        return true;
    }
    
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
    
    public User findById(Long id) {
        return userMapper.findById(id);
    }
}
