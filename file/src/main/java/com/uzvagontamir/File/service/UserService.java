package com.uzvagontamir.File.service;

import com.uzvagontamir.File.model.User;
import com.uzvagontamir.File.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
