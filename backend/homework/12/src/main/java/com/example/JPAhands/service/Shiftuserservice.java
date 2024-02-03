package com.example.JPAhands.service;

import com.example.JPAhands.exception.Datanotmatch;
import com.example.JPAhands.exception.Shiftnotfoundexp;
import com.example.JPAhands.exception.unprocesEntityexp;
import com.example.JPAhands.repositories.Shiftuserrepository;
import com.example.JPAhands.entities.ShiftUser;
import com.example.JPAhands.entities.Shift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class Shiftuserservice {
    private final Shiftuserrepository shiftUserRepository;

    @Autowired
    public Shiftuserservice(Shiftuserrepository shiftUserRepository) {
        this.shiftUserRepository = shiftUserRepository;
    }

    public void addShiftUser(ShiftUser shiftUser) {
        try {
            shiftUserRepository.save(shiftUser);
        } catch (Exception e) {
            throw new unprocesEntityexp("Cannot add shift-user.");
        }
    }

    public List<ShiftUser> getAllShiftUsers(UUID tenantId) {
        List<ShiftUser> shiftUserList;
        try {
            shiftUserList = shiftUserRepository.findAllByTenantId(tenantId);
        } catch (Exception e) {
            throw new Datanotmatch("Cannot find shift-users for tenantId");
        }
        return shiftUserList;
    }

    public void deleteShiftUser(UUID shiftUserId){
        Optional<ShiftUser> shiftUserOptional = shiftUserRepository.findById(shiftUserId);

        if (shiftUserOptional.isPresent()) {
            ShiftUser shiftUser = shiftUserOptional.get();
            Shift shift = shiftUser.getShift();
            LocalTime time = LocalTime.of(23,0,0);
            if (shift != null && shift.getTimeEnd().toLocalTime().equals(time)) {
                shiftUserRepository.delete(shiftUser);
            } else {
                throw new Shiftnotfoundexp("Shift does not end at 23:00 UTC");
            }
        } else {
            throw new Datanotmatch("Cannot find shift-user for shiftUserId");
        }
    }
}
