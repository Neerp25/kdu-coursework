package com.example.jdbc.service;

import com.example.jdbc.dto.Entitydto;
import com.example.jdbc.entities.Tenant;
import com.example.jdbc.exception.unprocesEntityexp;
import com.example.jdbc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Tanantservice {
    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;
    private final ShiftUserRepository shiftUserRepository;
    private final ShiftTypeRepository shiftTypeRepository;
    private final ShiftRepository shiftRepository;

    @Autowired
    public Tanantservice(TenantRepository tenantRepository, UserRepository userRepository, ShiftUserRepository shiftUserRepository, ShiftTypeRepository shiftTypeRepository, ShiftRepository shiftRepository) {
        this.tenantRepository = tenantRepository;
        this.userRepository = userRepository;
        this.shiftUserRepository = shiftUserRepository;
        this.shiftTypeRepository = shiftTypeRepository;
        this.shiftRepository = shiftRepository;
    }

    public List<Tenant> getAllTenants() {
        return tenantRepository.getAllTenants();
    }

    @Transactional
    public void addAllTenantEntities(Entitydto dto) {
        try {
            userRepository.addUser(dto.getUser());
            shiftRepository.addShift(dto.getShift());
            shiftTypeRepository.addShiftType(dto.getShiftType());
            shiftUserRepository.addShiftUser(dto.getShiftUser());
        } catch (Exception e) {
            throw new unprocesEntityexp("Cannot add details.");
        }
    }
}
