package com.example.JPAhands.service;

import com.example.JPAhands.entities.Shift;
import com.example.JPAhands.exception.Datanotmatch;
import com.example.JPAhands.exception.unprocesEntityexp;
import com.example.JPAhands.repositories.Shiftrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

@Service
public class Shiftservice {

    private  Shiftrepository shiftRepository;

    @Autowired
    public Shiftservice(Shiftrepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }


    public void addShiftType(Shift shift) {
        try {
             shiftRepository.save(shift);
        } catch (Exception e) {
            throw new unprocesEntityexp("Cannot add shift-type. Please check the entity.");
        }
    }

    public List<Shift> getAllShiftTypes(UUID tenantId) {
        List<Shift> shiftList;
        try {
            shiftList = shiftRepository.findAllByTenantId(tenantId);
        } catch (Exception e) {
            throw new Datanotmatch("Cannot find shift-types for tenantId. Please check again.");
        }
        return shiftList;
    }
    public List<Shift> getTop3ShiftsByDateRange(){
        List<Shift> shift;
        LocalDate dateStart = LocalDate.of(2023, 10, 1);
        LocalDate dateEnd = LocalDate.of(2023, 10, 20);
        try {
            shift = shiftRepository.findTop3ShiftsByDateRange(dateStart,dateEnd);
        } catch (Exception e) {
            throw new Datanotmatch("Cannot find shifts for date range");
        }
        return shift;
    }

}
