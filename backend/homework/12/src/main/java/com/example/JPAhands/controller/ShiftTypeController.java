package com.example.JPAhands.controller;

import com.example.JPAhands.entities.ShiftType;
import com.example.JPAhands.service.Shifttypeservice;
import dto.Requestdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Shift-Type")
public class ShiftTypeController {
    private final Shifttypeservice shiftTypeService;

    /**
     * Constructor for ShiftTypeController.
     */
    @Autowired
    public ShiftTypeController(Shifttypeservice shiftTypeService) {
        this.shiftTypeService = shiftTypeService;
    }

    /**
     PostMapping for adding new shiftType and Return ResponseEntity with success message
     */
    @PostMapping()
    public ResponseEntity<String> addShift(@RequestBody ShiftType shiftType) {
        shiftTypeService.addShiftType(shiftType);
        return new ResponseEntity<>("Shift-type added successfully", HttpStatus.CREATED);
    }
    /**
     GetMapping for Reterving all shiftType and Return ResponseEntity with List of ShiftType
     */
    @GetMapping()
    public ResponseEntity<List<ShiftType>> getAllShifts(@RequestBody Requestdto requestDTO) {
        List<ShiftType> shiftTypes = shiftTypeService.getAllShiftTypes(requestDTO.getTenantId());
        return ResponseEntity.ok(shiftTypes);
    }
}
