package com.example.JPAhands.service;

import com.example.JPAhands.entities.Tanant;
import com.example.JPAhands.exception.unprocesEntityexp;
import com.example.JPAhands.repositories.Tenantrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class Tanantservice {
    private final Tenantrepository tenantRepository;

    @Autowired
    public Tanantservice(Tenantrepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public void addTenant(Tanant tenant) {
        try {
            tenantRepository.save(tenant);
        } catch (Exception e) {
            throw new unprocesEntityexp("Tenant can't be added.");
        }
    }

    public List<Tanant> getAllTenants() {
        return tenantRepository.findAll();
    }
}
