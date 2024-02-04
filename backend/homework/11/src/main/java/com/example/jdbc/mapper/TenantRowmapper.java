package com.example.jdbc.mapper;

import com.example.jdbc.entities.Tenant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TenantRowmapper  implements RowMapper<Tenant>{
    @Override
    public Tenant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tenant tenant = new Tenant();
        tenant.setId(UUID.fromString(rs.getString("id")));
        tenant.setName(rs.getString("name"));
        tenant.setCreatedAt(rs.getTimestamp("created_at"));
        tenant.setCreatedBy(rs.getString("created_by"));
        tenant.setUpdatedAt(rs.getTimestamp("updated_at"));
        tenant.setUpdatedBy(rs.getString("updated_by"));
        tenant.setTimeZone(rs.getString("time_zone"));
        return tenant;
    }
}
