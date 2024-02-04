package com.example.jdbc.controller;

import com.example.jdbc.dto.Requestdto;
import com.example.jdbc.entities.Shifttype;
import com.example.jdbc.service.Shifttypeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/shift-type")
public class Shifttypecontroller {
    private final Shifttypeservice shiftTypeService;

    /**
     * Constructor for ShiftTypeController.
     */
    @Autowired
    public Shifttypecontroller(Shifttypeservice shiftTypeService) {
        this.shiftTypeService = shiftTypeService;
    }

    /**
     PostMapping for adding new shiftType and Return ResponseEntity with success message
     */
    @PostMapping()
    public ResponseEntity<String> addShift(@RequestBody Shifttype shiftType) {
        shiftTypeService.addShiftType(shiftType);
        return ResponseEntity.ok("Shift-type added successfully");
    }

    /**
     GetMapping for Reterving all shiftType and Return ResponseEntity with List of ShiftType
     */
    @GetMapping()
    public ResponseEntity<List<Shifttype>> getAllShifts(@RequestBody Requestdto requestDTO) {
        List<Shifttype> shiftTypes = shiftTypeService.getAllShiftTypes(requestDTO.getTenantId());
        return ResponseEntity.ok(shiftTypes);
    }
}
