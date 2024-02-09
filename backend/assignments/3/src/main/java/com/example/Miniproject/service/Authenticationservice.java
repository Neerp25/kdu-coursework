package com.example.Miniproject.service;

import com.example.Miniproject.Mapfunc.Mapper;
import com.example.Miniproject.dto.request.registerrequest.Userregisterreqdto;
import com.example.Miniproject.dto.response.registerresponse.Userregisterresdto;
import com.example.Miniproject.entity.model.User;
import com.example.Miniproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Authenticationservice {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final Jwtservice jwtService;

    /**
     * Constructs an AuthenticationService with the specified dependencies..
     */
    public Authenticationservice(UserRepository repository, PasswordEncoder passwordEncoder, Jwtservice jwtService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    /**
     * Registers a new user with the provided user registration details.
     */
    public Userregisterresdto register(Userregisterreqdto userRegisterRequestDTO) {
        userRegisterRequestDTO.setPassword(passwordEncoder.encode(userRegisterRequestDTO.getPassword()));

        User user = repository.save(Mapper.mapToUser(userRegisterRequestDTO));

        String jwtToken = jwtService.generateToken(user);

        return new Userregisterresdto("User registered successfully", jwtToken);
    }
}
