package com.example.demo.controller;

import com.example.demo.ConsoleLogger;
import com.example.demo.dto.Cordinatesdto;
import com.example.demo.services.Geocodingservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/geocoding")
public class Geocodingcont {

    private final Geocodingservice geocodingService;


    @Autowired
    public Geocodingcont(Geocodingservice s) {
        this.geocodingService = s;
    }

    /**
     * Endpoint for getting coordinates based on the provided address
     */
    @GetMapping
    public Cordinatesdto getCoordinates(@RequestParam String address) {
        Cordinatesdto coordinatesDTO = geocodingService.getCoordinates(address);
        log.info("The co-ordinates corresponding to " + address + " are: " + coordinatesDTO.print() + "\n");
        return coordinatesDTO;
    }
}