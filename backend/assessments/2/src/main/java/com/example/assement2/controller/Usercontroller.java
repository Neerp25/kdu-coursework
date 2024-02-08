package com.example.assement2.controller;

import com.example.assement2.entity.User;
import com.example.assement2.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class Usercontroller {

    private final Userservice userservice;
    @Autowired
    public Usercontroller(Userservice userservice){
        this.userservice=userservice;
    }
    @PostMapping("/add")
    public ResponseEntity<String> adduser(@RequestBody User User) {
        userservice.adduser(User);
        return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
    }
    @GetMapping("/get")
    public ResponseEntity<List<User>> getStudentByID() {
        List<User> data = userservice.findalluser();
        return ResponseEntity.ok(data);
    }




}
