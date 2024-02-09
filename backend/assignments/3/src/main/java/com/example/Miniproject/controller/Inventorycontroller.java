package com.example.Miniproject.controller;

import com.example.Miniproject.dto.request.entityrequest.Inventoryreqdto;
import com.example.Miniproject.dto.response.entityresponse.Inventoryresdto;
import com.example.Miniproject.dto.response.entityresponse.Responsedto;
import com.example.Miniproject.service.Inventoryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/inventory")
public class Inventorycontroller {
    private final Inventoryservice inventoryService;

    @Autowired
    public Inventorycontroller(Inventoryservice inventoryService) {
        this.inventoryService = inventoryService;
    }
    /**
     * Add new Item to Inventory
     */
    @PostMapping
    public ResponseEntity<Responsedto> addInventoryItem(@Valid @RequestBody Inventoryreqdto inventoryRequestDTO) {
        return ResponseEntity.ok(inventoryService.addInventoryItem(inventoryRequestDTO));
    }
    /**
     * Get All items Available in Inventory
     */
    @GetMapping
    public ResponseEntity<Inventoryresdto> getInventoryItems() {
        return ResponseEntity.ok(inventoryService.getInventoryItems());
    }

}
