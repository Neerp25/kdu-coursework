package com.example.jdbc.controller;

import com.example.jdbc.dto.Requestdto;
import com.example.jdbc.entities.Shift;
import com.example.jdbc.service.Shiftservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/shift")
public class Shiftcontroller {

    private final Shiftservice shiftService;
    /**
     * Constructor for ShiftController.
     */

    @Autowired
    public Shiftcontroller(Shiftservice shiftService) {
        this.shiftService = shiftService;
    }

    /**
     PostMapping for adding new shift and Return ResponseEntity with success message
     */
    @PostMapping()
    public ResponseEntity<String> addShift(@RequestBody Shift shift) {
        shiftService.addShift(shift);
        return ResponseEntity.ok("Shift added successfully");
    }

    /**
     GetMapping for Reterving all shifts and Return ResponseEntity with List of Shifts
     */
    @GetMapping()
    public ResponseEntity<List<Shift>> getAllShifts(@RequestBody Requestdto requestDTO) {
        List<Shift> shifts = shiftService.getAllShifts(requestDTO.getTenantId());
        return ResponseEntity.ok(shifts);
    }
}
