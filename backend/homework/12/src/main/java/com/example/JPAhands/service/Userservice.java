package com.example.JPAhands.service;

import com.example.JPAhands.entities.User;
import com.example.JPAhands.exception.Datanotmatch;
import com.example.JPAhands.exception.unprocesEntityexp;
import com.example.JPAhands.repositories.Userrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.UUID;

@Service
public class Userservice {
    private final Userrepository userRepository;

    @Autowired
    public Userservice(Userrepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new unprocesEntityexp("can't add use! check entity again.");
        }
    }

    public List<User> getAllUsers(UUID tenantId) {
        List<User> userList;
        try {
            userList = userRepository.findAllByTenantId(tenantId);
        } catch (Exception e) {
            throw new Datanotmatch("Data not match users for tenantId! check again");
        }
        return userList;
    }


    public void updateUser(UUID userId, User user) {
        try {
            int cnt = 0;
            cnt = userRepository.updateUserDetails(userId,user.getUsername(),user.getLoggedIn(),user.getTimeZone());
            if(cnt == 0){
                throw new Datanotmatch("Data Not Matched!");
            }
        } catch (Exception e) {
            throw new Datanotmatch("Data Not Matched! Please check again.");
        }
    }

    public Page<User> getAllUsersPagination(Pageable pageable){
        return userRepository.findAll(pageable);
    }
}
