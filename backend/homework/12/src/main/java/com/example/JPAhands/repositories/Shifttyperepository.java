package com.example.JPAhands.repositories;

import com.example.JPAhands.entities.Shift;
import com.example.JPAhands.entities.ShiftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;
@Repository
public interface Shifttyperepository extends JpaRepository<ShiftType, UUID> {
    List<ShiftType> findAllByTenantId(UUID tenantId);

}
