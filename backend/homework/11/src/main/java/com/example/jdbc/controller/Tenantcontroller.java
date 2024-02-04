package com.example.jdbc.controller;

import com.example.jdbc.dto.Entitydto;
import com.example.jdbc.entities.Tenant;
import com.example.jdbc.service.Tanantservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenant")
public class Tenantcontroller {

    private final Tanantservice tenantService;

    /**
     * Constructor for TenantController.
     */
    @Autowired
    public Tenantcontroller(Tanantservice tenantService) {
        this.tenantService = tenantService;
    }

    /**
     GetMapping for Reterving all Tanant and Return ResponseEntity with List of All Tenants
     */
    @GetMapping()
    public ResponseEntity<List<Tenant>> getAllTenants() {
        return ResponseEntity.ok(tenantService.getAllTenants());
    }

    /**
     PostMapping for adding new Tenant and Return ResponseEntity with success message
     */
    @PostMapping()
    public ResponseEntity<String> addAllTenantEntities(@RequestBody Entitydto dto) {
        tenantService.addAllTenantEntities(dto);
        return new ResponseEntity<>("All details added successfully", HttpStatus.OK);
    }
}
