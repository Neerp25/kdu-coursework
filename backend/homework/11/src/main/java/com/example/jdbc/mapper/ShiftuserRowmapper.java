package com.example.jdbc.mapper;

import com.example.jdbc.entities.Shiftuser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ShiftuserRowmapper implements RowMapper<Shiftuser>{
    @Override
    public Shiftuser mapRow(ResultSet rs, int rowNum) throws SQLException {
        Shiftuser shiftUser = new Shiftuser();
        shiftUser.setId(rs.getObject("id", UUID.class));
        shiftUser.setShiftId(rs.getObject("shift_id", UUID.class));
        shiftUser.setUserId(rs.getObject("user_id", UUID.class));
        shiftUser.setCreatedAt(rs.getTimestamp("created_at"));
        shiftUser.setCreatedBy(rs.getString("created_by"));
        shiftUser.setUpdatedAt(rs.getTimestamp("updated_at"));
        shiftUser.setUpdatedBy(rs.getString("updated_by"));
        shiftUser.setTenantId(rs.getObject("tenant_id", UUID.class));
        return shiftUser;
    }
}
