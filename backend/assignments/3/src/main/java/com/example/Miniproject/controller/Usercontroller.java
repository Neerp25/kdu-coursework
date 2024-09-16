package com.example.Miniproject.controller;

import com.example.Miniproject.dto.request.registerrequest.Userregisterreqdto;
import com.example.Miniproject.dto.response.registerresponse.Userregisterresdto;
import com.example.Miniproject.service.Authenticationservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class Usercontroller {

    private final Authenticationservice authenticationService;

    public Usercontroller(Authenticationservice authenticationService) {
        this.authenticationService = authenticationService;
    }
    /**
     Handles the Registration of User
     */
    @PostMapping("/register")
    public ResponseEntity<Userregisterresdto> registerUser(@Valid @RequestBody Userregisterreqdto userRegisterRequestDTO) {
        try {
            return ResponseEntity.ok(authenticationService.register(userRegisterRequestDTO));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
