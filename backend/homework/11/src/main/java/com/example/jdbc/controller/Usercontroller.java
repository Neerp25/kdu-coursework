package com.example.jdbc.controller;

import com.example.jdbc.dto.Requestdto;
import com.example.jdbc.entities.User;
import com.example.jdbc.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/user")
public class Usercontroller {

    private final Userservice userService;

    /**
     * Constructor for UserController.
     */
    @Autowired
    public Usercontroller(Userservice userService) {
        this.userService = userService;
    }

    /**
     PostMapping for adding new User and Return ResponseEntity with success message
     */
    @PostMapping()
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>("User added successfully", HttpStatus.OK);
    }

    /**
     GetMapping for Reterving all Users and Return ResponseEntity with List of All Users
     */
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(@RequestBody Requestdto requestDTO) {
        List<User> users = userService.getAllUsers(requestDTO.getTenantId());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     PutMapping to update user with userid and Return ResponseEntity with User updated Successfully
     */
    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable UUID userId, @RequestBody User user) {
        userService.updateUser(userId, user);
        return ResponseEntity.ok("User updated successfully");
    }
}
