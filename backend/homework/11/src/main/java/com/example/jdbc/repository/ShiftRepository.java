package com.example.jdbc.repository;

import com.example.jdbc.entities.Shift;
import com.example.jdbc.mapper.ShiftRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.List;

@Repository
public class ShiftRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void addShift(Shift shift) {
        String sql = "INSERT INTO shifts (id, shift_type_id, name, date_start, date_end, time_start, time_end, created_at, created_by, updated_at, updated_by, time_zone, tenant_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, UUID.randomUUID(), shift.getShiftTypeId(), shift.getName(), shift.getDateStart(), shift.getDateEnd(), shift.getTimeStart(), shift.getTimeEnd(), shift.getCreatedAt(), shift.getCreatedBy(), shift.getUpdatedAt(), shift.getUpdatedBy(), shift.getTimeZone(), shift.getTenantId());
    }

    public List<Shift> getAllShifts(UUID tenantId) {
        String sql = "SELECT * FROM shifts WHERE tenant_id = ?";
        return jdbcTemplate.query(sql, new ShiftRowmapper(), tenantId);
    }
}
