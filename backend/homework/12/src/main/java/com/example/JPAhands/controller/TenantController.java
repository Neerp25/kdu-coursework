package com.example.JPAhands.controller;

import com.example.JPAhands.entities.Tanant;
import com.example.JPAhands.service.Tanantservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tenant")
public class TenantController {
    private final Tanantservice tenantService;
    /**
     * Constructor for TenantController.
     */
    @Autowired
    public TenantController(Tanantservice tenantService) {
        this.tenantService = tenantService;
    }
    /**
     PostMapping for adding new Tenant and Return ResponseEntity with success message
     */
    @PostMapping()
    public ResponseEntity<String> addTenant(@RequestBody Tanant tenant) {
        tenantService.addTenant(tenant);
        return new ResponseEntity<>("Tenant added successfully", HttpStatus.CREATED);
    }
    /**
     GetMapping for Reterving all Tanant and Return ResponseEntity with List of All Tenants
     */
    @GetMapping()
    public ResponseEntity<List<Tanant>> getAllTenants() {
        return ResponseEntity.ok(tenantService.getAllTenants());
    }
}
