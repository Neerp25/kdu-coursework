package com.example.jdbc.service;

import com.example.jdbc.entities.Shifttype;
import com.example.jdbc.exception.Datanotmatch;
import com.example.jdbc.exception.unprocesEntityexp;
import com.example.jdbc.repository.ShiftTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.UUID;

@Repository
public class Shifttypeservice {

    private final ShiftTypeRepository shiftTypeRepository;

    @Autowired
    public Shifttypeservice(ShiftTypeRepository shiftTypeRepository) {
        this.shiftTypeRepository = shiftTypeRepository;
    }


    public void addShiftType(Shifttype shiftType) {
        try {
            shiftTypeRepository.addShiftType(shiftType);
        } catch (Exception e) {
            throw new unprocesEntityexp("Cannot add shift-type.");
        }
    }


    public List<Shifttype> getAllShiftTypes(UUID tenantId) {
        List<Shifttype> shiftTypeList;
        try {
            shiftTypeList = shiftTypeRepository.getAllShiftTypes(tenantId);
        } catch (Exception e) {
            throw new Datanotmatch("Cannot find shift-types for tenantId.");
        }
        return shiftTypeList;
    }
}
