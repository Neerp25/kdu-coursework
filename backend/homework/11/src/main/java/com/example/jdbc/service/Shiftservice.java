package com.example.jdbc.service;

import com.example.jdbc.entities.Shift;
import com.example.jdbc.exception.Datanotmatch;
import com.example.jdbc.exception.unprocesEntityexp;
import com.example.jdbc.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
public class Shiftservice {

    private final ShiftRepository shiftRepository;

    @Autowired
    public Shiftservice(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public void addShift(Shift shift) {
        try {
            shiftRepository.addShift(shift);
        } catch (Exception e) {
            throw new unprocesEntityexp("Cannot add shift.");
        }
    }

    public List<Shift> getAllShifts(UUID tenantId) {
        List<Shift> shiftList;
        try {
            shiftList = shiftRepository.getAllShifts(tenantId);
        } catch (Exception e) {
            throw new Datanotmatch("Cannot find shifts for tenantId.");
        }
        return shiftList;
    }
}
