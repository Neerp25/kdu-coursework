package com.example.jdbc.repository;

import com.example.jdbc.entities.Shiftuser;
import com.example.jdbc.mapper.ShiftuserRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.List;

@Repository
public class ShiftUserRepository {

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public ShiftUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Transactional
    public void addShiftUser(Shiftuser shiftUser) {
        String sql = "INSERT INTO shift_user (id, shift_id, user_id, created_at, created_by, updated_at, updated_by, tenant_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, UUID.randomUUID(), shiftUser.getShiftId(), shiftUser.getUserId(), shiftUser.getCreatedAt(), shiftUser.getCreatedBy(), shiftUser.getUpdatedAt(), shiftUser.getUpdatedBy(), shiftUser.getTenantId());
    }


    public List<Shiftuser> getAllShiftUsers(UUID tenantId) {
        String sql = "SELECT * FROM shift_user WHERE tenant_id = ?";
        return jdbcTemplate.query(sql, new ShiftuserRowmapper(),tenantId);
    }
}
