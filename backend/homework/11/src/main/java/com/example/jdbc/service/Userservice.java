package com.example.jdbc.service;

import com.example.jdbc.entities.User;
import com.example.jdbc.exception.Datanotmatch;
import com.example.jdbc.exception.unprocesEntityexp;
import com.example.jdbc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
public class Userservice {

    private final UserRepository userRepository;


    @Autowired
    public Userservice(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void addUser(User user) {
        try {
            userRepository.addUser(user);
        } catch (Exception e) {
            throw new unprocesEntityexp("Cannot add user.");
        }
    }

    public List<User> getAllUsers(UUID tenantId) {
        List<User> userList;
        try {
            userList = userRepository.getAllUsers(tenantId);
        } catch (Exception e) {
            throw new Datanotmatch("Cannot find users for tenantId.");
        }
        return userList;
    }

    public void updateUser(UUID userId, User user) {
        try {
            userRepository.updateUser(userId, user);
        } catch (Exception e) {
            throw new Datanotmatch("Cannot find user with userId.");
        }
    }
}
