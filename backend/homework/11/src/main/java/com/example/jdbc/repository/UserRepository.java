package com.example.jdbc.repository;

import com.example.jdbc.entities.User;
import com.example.jdbc.mapper.UserRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.UUID;
import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Transactional
    public void addUser(User user) {
        String sql = "INSERT INTO users (id, username, loggedin, created_at, created_by, updated_at, updated_by, time_zone, tenant_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, UUID.randomUUID(), user.getUsername(), user.getLoggedIn(), new Timestamp(System.currentTimeMillis()), user.getCreatedBy(), user.getUpdatedAt(), user.getUpdatedBy(), user.getTimeZone(), user.getTenantId());
    }

    public List<User> getAllUsers(UUID tenantId) {
        String sql = "SELECT * FROM users WHERE tenant_id = ?";
        return jdbcTemplate.query(sql,new UserRowmapper(),tenantId);
    }


    public void updateUser(UUID userId, User user) {
        String sql = "UPDATE users SET username = ?, tenant_id = ?, updated_by = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getTenantId(), user.getUpdatedBy(), new Timestamp(System.currentTimeMillis()), userId);
    }
}
