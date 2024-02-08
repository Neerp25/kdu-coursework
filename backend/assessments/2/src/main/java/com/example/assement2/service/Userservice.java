package com.example.assement2.service;


import com.example.assement2.Reposistory.UserReposistory;

import com.example.assement2.entity.User;
import com.example.assement2.exception.Eventnotfoundexp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Userservice {
    private UserReposistory userReposistory;
    @Autowired
    public Userservice(UserReposistory userReposistory){
        this.userReposistory=userReposistory;
    }
    public void adduser(User user){
        userReposistory.save(user);

    }
    public List<User> findalluser(){
        try{
            return userReposistory.findAll();
        }catch (Exception e) {
            throw new Eventnotfoundexp("Event not found");
        }
    }

}
