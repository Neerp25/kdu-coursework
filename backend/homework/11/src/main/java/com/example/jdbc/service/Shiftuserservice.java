package com.example.jdbc.service;

import com.example.jdbc.entities.Shiftuser;
import com.example.jdbc.exception.Datanotmatch;
import com.example.jdbc.exception.unprocesEntityexp;
import com.example.jdbc.repository.ShiftUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
public class Shiftuserservice {

    private final ShiftUserRepository shiftUserRepository;


    @Autowired
    public Shiftuserservice(ShiftUserRepository shiftUserRepository) {
        this.shiftUserRepository = shiftUserRepository;
    }


    public void addShiftUser(Shiftuser shiftUser) {
        try {
            shiftUserRepository.addShiftUser(shiftUser);
        } catch (Exception e) {
            throw new unprocesEntityexp("Cannot add shift-user.");
        }
    }

    public List<Shiftuser> getAllShiftUsers(UUID tenantId) {
        List<Shiftuser> shiftUserList;
        try {
            shiftUserList = shiftUserRepository.getAllShiftUsers(tenantId);
        } catch (Exception e) {
            throw new Datanotmatch("Cannot find shift-users for tenantId.");
        }
        return shiftUserList;
    }
}
