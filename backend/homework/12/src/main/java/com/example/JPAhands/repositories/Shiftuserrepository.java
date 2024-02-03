package com.example.JPAhands.repositories;

import com.example.JPAhands.entities.Shift;
import com.example.JPAhands.entities.ShiftType;
import com.example.JPAhands.entities.ShiftUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface Shiftuserrepository extends JpaRepository<ShiftUser, UUID> {
    List<ShiftUser> findAllByTenantId(UUID tenantId);

}
