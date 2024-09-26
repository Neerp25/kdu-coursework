package com.example.JPAhands.service;

import com.example.JPAhands.entities.ShiftType;
import com.example.JPAhands.exception.Datanotmatch;
import com.example.JPAhands.exception.unprocesEntityexp;
import com.example.JPAhands.repositories.Shifttyperepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class Shifttypeservice {

    private  Shifttyperepository shiftTypeRepository;

    @Autowired
    public Shifttypeservice(Shifttyperepository shiftTypeRepository) {
        this.shiftTypeRepository = shiftTypeRepository;
    }

    public void addShiftType(ShiftType shiftType) {
        try {
            shiftTypeRepository.save(shiftType);
        } catch (Exception e) {
            throw new unprocesEntityexp("Cannot add shift-type. Please check the entity.");
        }
    }

    public List<ShiftType> getAllShiftTypes(UUID tenantId) {
        List<ShiftType> shiftTypeList;
        try {
           shiftTypeList = shiftTypeRepository.findAllByTenantId(tenantId);
        } catch (Exception e) {
            throw new Datanotmatch("Cannot find shift-types for tenantId. Please check again.");
        }
        return shiftTypeList;
    }
}
