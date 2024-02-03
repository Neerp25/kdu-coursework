package com.example.JPAhands.controller;

import com.example.JPAhands.entities.Shift;
import com.example.JPAhands.entities.ShiftType;
import com.example.JPAhands.service.Shiftservice;
import dto.Requestdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Shift")
public class ShiftController {

    private final Shiftservice shiftService;

    /**
     * Constructor for ShiftController.
     */
    @Autowired
    public ShiftController(Shiftservice shiftService) {
        this.shiftService = shiftService;
    }

    /**
     PostMapping for adding new shift and Return ResponseEntity with success message
     */

    @PostMapping()
    public ResponseEntity<String> addShift(@RequestBody ShiftType shift) {
        shiftService.addShiftType(shift);
        return new ResponseEntity<>("Shift added successfully", HttpStatus.CREATED);
    }
    /**
     GetMapping for Reterving all shifts and Return ResponseEntity with List of Shifts
     */
    @GetMapping()
    public ResponseEntity<List<ShiftType>> getAllShifts(@RequestBody Requestdto requestDTO) {
        List<ShiftType> shifts = shiftService.getAllShiftTypes(requestDTO.getTenantId());
        return ResponseEntity.ok(shifts);
    }
    /**
     GetMapping for Reterving Top 3 shift By Date Range and Return ResponseEntity with List of Top 3 Shifts
     */
    @GetMapping("/top3")
    public ResponseEntity<List<Shift>> getTop3ShiftsByDateRange() {
        List<Shift> shifts = shiftService.getTop3ShiftsByDateRange();
        return ResponseEntity.ok(shifts);
    }
}
