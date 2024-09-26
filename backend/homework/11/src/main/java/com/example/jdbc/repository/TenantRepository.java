package com.example.jdbc.repository;

import com.example.jdbc.entities.Tenant;
import com.example.jdbc.mapper.TenantRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TenantRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TenantRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Tenant> getAllTenants() {
        String sql = "SELECT * FROM tenants";
        return jdbcTemplate.query(sql, new TenantRowmapper());
    }
}
