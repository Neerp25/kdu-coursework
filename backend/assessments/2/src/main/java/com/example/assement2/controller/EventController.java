package com.example.assement2.controller;

import com.example.assement2.entity.Eventcatalog;
import com.example.assement2.entity.User;
import com.example.assement2.service.Eventcatalogservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private final Eventcatalogservice eventcatalogservice;
    @Autowired
    public EventController(Eventcatalogservice eventcatalogservice){
        this.eventcatalogservice=eventcatalogservice;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addevent(@RequestBody Eventcatalog event) {
        eventcatalogservice.addevent(event);
        return new ResponseEntity<>("Event added successfully", HttpStatus.CREATED);
    }
    @GetMapping("/get")
    public ResponseEntity<List<Eventcatalog>> getallevent() {
        List<Eventcatalog> data = eventcatalogservice.findallevent();
        return ResponseEntity.ok(data);
    }
}
