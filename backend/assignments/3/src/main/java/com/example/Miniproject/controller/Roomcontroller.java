package com.example.Miniproject.controller;

import com.example.Miniproject.dto.request.addrequest.AddRoomreqdto;
import com.example.Miniproject.dto.response.entityresponse.Roomresdto;
import com.example.Miniproject.service.Roomservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/room")
public class Roomcontroller {
    private final Roomservice roomService;

    @Autowired
    public Roomcontroller(Roomservice roomService) {
        this.roomService = roomService;
    }
    /**
     *  Adding new Room to House System
     */
    @PostMapping
    public ResponseEntity<Roomresdto> addRoomToHouse(@Valid @RequestHeader String authorization,
                                                     @Valid @RequestParam String houseId,
                                                     @Valid @RequestBody AddRoomreqdto addRoomRequestDTO) {
        return ResponseEntity.ok(roomService.addRoomToHouse(houseId, addRoomRequestDTO, authorization));
    }
}
