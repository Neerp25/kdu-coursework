package com.example.JPAhands.controller;

import com.example.JPAhands.entities.User;
import com.example.JPAhands.service.Userservice;
import dto.Requestdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final Userservice userService;
    /**
     * Constructor for UserController.
     */
    @Autowired
    public UserController(Userservice userService) {
        this.userService = userService;
    }

    /**
     PostMapping for adding new User and Return ResponseEntity with success message
     */
    @PostMapping()
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
    }
    /**
     GetMapping for Reterving all Users and Return ResponseEntity with List of All Users
     */
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(@RequestBody Requestdto requestDTO) {
        List<User> users = userService.getAllUsers(requestDTO.getTenantId());
        return ResponseEntity.ok(users);
    }
    /**
     PutMapping to update user with userid and Return ResponseEntity with User updated Successfully
     */
    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable UUID userId, @RequestBody User user) {
        userService.updateUser(userId, user);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }
    /**
     GetMapping for Reterving all User from given pageNo with size
     */
    @GetMapping("/all")
    public Page<User> getAllUsersPagination(@RequestParam int pageNo, @RequestParam int size){
        pageNo = Math.max(0, pageNo);
        size = Math.min(80, Math.max(1, size));
        PageRequest pageable = PageRequest.of(pageNo, size);
        return userService.getAllUsersPagination(pageable);
    }
}
