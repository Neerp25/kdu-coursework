package com.example.Miniproject.service;

import com.example.Miniproject.expection.Datanotmatchexp;
import com.example.Miniproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class Userservice implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    public Userservice(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username).orElseThrow(() -> new Datanotmatchexp("User does not Match!"));
    }
}
