package com.example.Miniproject.controller;

import com.example.Miniproject.dto.request.addrequest.AddDevicereqdto;
import com.example.Miniproject.dto.request.registerrequest.Deviceregisterreqdto;
import com.example.Miniproject.dto.response.entityresponse.Responsedto;
import com.example.Miniproject.service.Deviceservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/device")
public class Devicecontroller {
    private final Deviceservice deviceService;

    @Autowired
    public Devicecontroller(Deviceservice deviceService) {
        this.deviceService = deviceService;
    }

    /**
     * Adds a device to a house.
     */
    @PostMapping("/add")
    public ResponseEntity<Responsedto> addDeviceToHouse(@Valid @RequestHeader String authorization,
                                                        @Valid @RequestBody AddDevicereqdto addDeviceRequestDTO) {
        return ResponseEntity.ok(deviceService.addDeviceToHouse(addDeviceRequestDTO, authorization));
    }
    /**
     * Register a new device in the system.
     */
    @PostMapping("/register")
    public ResponseEntity<Responsedto> registerDevice(@Valid @RequestHeader String authorization,
                                                      @Valid @RequestBody Deviceregisterreqdto deviceRegisterRequestDTO) {
        return ResponseEntity.ok(deviceService.registerDevice(deviceRegisterRequestDTO, authorization));
    }
}
