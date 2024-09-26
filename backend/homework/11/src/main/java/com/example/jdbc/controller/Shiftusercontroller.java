package com.example.jdbc.controller;

import com.example.jdbc.dto.Requestdto;
import com.example.jdbc.entities.Shiftuser;
import com.example.jdbc.service.Shiftuserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shift-user")
public class Shiftusercontroller {

    private final Shiftuserservice shiftUserService;

    /**
     * Constructor for ShiftUserController.
     */
    @Autowired
    public Shiftusercontroller(Shiftuserservice shiftUserService) {
        this.shiftUserService = shiftUserService;
    }

    /**
     PostMapping for adding new shiftUser and Return ResponseEntity with success message
     */
    @PostMapping()
    public ResponseEntity<String> addShift(@RequestBody Shiftuser shiftUser) {
        shiftUserService.addShiftUser(shiftUser);
        return ResponseEntity.ok("Shift-user added successfully");
    }

    /**
     GetMapping for Reterving all shiftUser and Return ResponseEntity with List of ShiftUser
     */
    @GetMapping()
    public ResponseEntity<List<Shiftuser>> getAllShiftUsers(@RequestBody Requestdto requestDTO) {
        List<Shiftuser> shiftUsers = shiftUserService.getAllShiftUsers(requestDTO.getTenantId());
        return ResponseEntity.ok(shiftUsers);
    }

}
