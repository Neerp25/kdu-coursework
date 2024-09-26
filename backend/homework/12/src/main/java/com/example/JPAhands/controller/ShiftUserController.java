package com.example.JPAhands.controller;

import com.example.JPAhands.entities.ShiftUser;
import com.example.JPAhands.service.Shiftuserservice;
import dto.Requestdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shift-user")
public class ShiftUserController {
    private final Shiftuserservice shiftUserService;

    /**
     * Constructor for ShiftUserController.
     */
    @Autowired
    public ShiftUserController(Shiftuserservice shiftUserService) {
        this.shiftUserService = shiftUserService;
    }

    /**
     PostMapping for adding new shiftUser and Return ResponseEntity with success message
     */
    @PostMapping()
    public ResponseEntity<String> addShiftUser(@RequestBody ShiftUser shiftUser) {
        shiftUserService.addShiftUser(shiftUser);
        return new ResponseEntity<>("Shift-user added successfully", HttpStatus.CREATED);
    }
    /**
     GetMapping for Reterving all shiftUser and Return ResponseEntity with List of ShiftUser
     */
    @GetMapping()
    public ResponseEntity<List<ShiftUser>> getAllShiftUsers(@RequestBody Requestdto requestDTO) {
        List<ShiftUser> shiftUsers = shiftUserService.getAllShiftUsers(requestDTO.getTenantId());
        return ResponseEntity.ok(shiftUsers);
    }
    /**
     DeleteMapping for Deleting shiftUser based On ID and Return ResponseEntity with msg deleted sucessfully
     */
    @DeleteMapping("/{shiftUserId}")
    public ResponseEntity<String> deleteShiftUser(@PathVariable UUID shiftUserId) {
        shiftUserService.deleteShiftUser(shiftUserId);
        return new ResponseEntity<>("Shift-user deleted successfully", HttpStatus.OK);
    }
}
