package com.example.JPAhands.repositories;

import com.example.JPAhands.entities.Tanant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface Tenantrepository extends JpaRepository<Tanant, UUID> {
    List<Tanant> findAllByTenantId(UUID tenantId);
}
