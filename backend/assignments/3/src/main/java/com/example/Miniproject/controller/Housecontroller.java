package com.example.Miniproject.controller;

import com.example.Miniproject.dto.request.addrequest.AddUserreqdto;
import com.example.Miniproject.dto.request.modifyrequest.Updateadressreqdto;
import com.example.Miniproject.dto.request.registerrequest.Houseregisterreqdto;
import com.example.Miniproject.dto.response.entityresponse.Responsedto;
import com.example.Miniproject.dto.response.getresponse.Getallhouseresdto;
import com.example.Miniproject.dto.response.getresponse.Getroomdevresdto;
import com.example.Miniproject.dto.response.registerresponse.Houseregisterresdto;
import com.example.Miniproject.service.Houseservice;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/house")
public class Housecontroller {
    private final Houseservice houseService;

    @Autowired
    public Housecontroller(Houseservice houseService) {
        this.houseService = houseService;
    }

    /**
     * get details of a specific house.
     */
    @GetMapping("/{houseId}")
    public ResponseEntity<Getroomdevresdto> getHouseDetails(@Valid @PathVariable("houseId") String houseId) {
        return ResponseEntity.ok(houseService.getHouseDetails(houseId));
    }

    /**
     * Adds a user to a house.
     */
    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<Responsedto> addUserToHouse(@Valid @RequestHeader String authorization,
                                                      @Valid @PathVariable String houseId,
                                                      @Valid @RequestBody AddUserreqdto addUserToHouseRequestDTO)
            throws JsonProcessingException {
        return ResponseEntity.ok(houseService.addUserToHouse(houseId, addUserToHouseRequestDTO, authorization));
    }

    /**
     * Adds a new house in the system.
     *
     */
    @PostMapping
    public ResponseEntity<Houseregisterresdto> addHouse(@Valid @RequestHeader String authorization,
                                                        @Valid @RequestBody Houseregisterreqdto houseRegisterRequestDTO) {
        return ResponseEntity.ok(houseService.addHouse(houseRegisterRequestDTO, authorization));
    }

    /**
     * Get all houses in the system.
     */
    @GetMapping
    public ResponseEntity<Getallhouseresdto> getAllHouses() {
        return ResponseEntity.ok(houseService.getAllHouses());
    }

    /**
     * Updates the address of a house with specify houseId
     */
    @PutMapping
    public ResponseEntity<Responsedto> updateHouseAddress(@Valid @RequestHeader String authorization,
                                                          @Valid @RequestParam String houseId,
                                                          @Valid @RequestBody Updateadressreqdto houseDTO) {
        return ResponseEntity.ok(houseService.updateHouseAddress(houseId, houseDTO, authorization));
    }

}
