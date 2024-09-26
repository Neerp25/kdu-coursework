package com.example.jdbc.repository;

import com.example.jdbc.entities.Shifttype;
import com.example.jdbc.mapper.ShifttypeRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.List;

@Repository

public class ShiftTypeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftTypeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void addShiftType(Shifttype shiftType) {
        String sql = "INSERT INTO shift_types (id, uq_name, description, active, created_at, created_by, updated_at, updated_by, time_zone, tenant_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, UUID.randomUUID(), shiftType.getUniqueName(), shiftType.getDescription(), shiftType.isActive(), shiftType.getCreatedAt(), shiftType.getCreatedBy(), shiftType.getUpdatedAt(), shiftType.getUpdatedBy(), shiftType.getTimeZone(), shiftType.getTenantId());
    }

    public List<Shifttype> getAllShiftTypes(UUID tenantId) {
        String sql = "SELECT * FROM shift_types WHERE tenant_id = ?";
        return jdbcTemplate.query(sql, new ShifttypeRowmapper(),tenantId);
    }
}
