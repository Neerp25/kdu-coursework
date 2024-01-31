package com.example.demo.controller;

import com.example.demo.dto.Addressdto;
import com.example.demo.services.RevGeocodingservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/reverse-geocoding")
public class RevGeocodingcont {

    private final RevGeocodingservice reverseGeocodingService;

    @Autowired
    public RevGeocodingcont(RevGeocodingservice reverseGeocodingService) {
        this.reverseGeocodingService = reverseGeocodingService;
    }

    /**
     * Endpoint for getting address based on the provided latitude and longitude coordinates
     */
    @GetMapping
    public String getAddress(@RequestParam double latitude, @RequestParam double longitude){
        Addressdto addressDTO = reverseGeocodingService.getAddress(latitude,longitude);
        log.info("The address corresponding to [latitude: " + latitude + ", longitude: " + longitude + "] is: " + addressDTO.getAddress() + "\n");
        return addressDTO.getAddress();
    }
}
