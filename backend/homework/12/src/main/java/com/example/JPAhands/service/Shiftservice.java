package com.example.JPAhands.service;

import com.example.JPAhands.entities.Shift;
import com.example.JPAhands.entities.ShiftType;
import com.example.JPAhands.exception.Datanotmatch;
import com.example.JPAhands.exception.unprocesEntityexp;
import com.example.JPAhands.repositories.Shiftrepository;
import com.example.JPAhands.repositories.Shifttyperepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

@Service
public class Shiftservice {

    private  Shifttyperepository shiftTypeRepository;

    @Autowired
    public Shiftservice(Shifttyperepository shiftTypeRepository) {
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
    public List<Shift> getTop3ShiftsByDateRange(){
        List<Shift> shiftList;
        LocalDate dateStart = LocalDate.of(2023, 10, 1);
        LocalDate dateEnd = LocalDate.of(2023, 10, 20);
        try {
            shiftList = Shiftrepository.findTop3ShiftsByDateRange(dateStart,dateEnd);
        } catch (Exception e) {
            throw new Datanotmatch("Cannot find shifts for date range");
        }
        return shiftList;
    }

}
