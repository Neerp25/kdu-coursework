package com.example.jdbc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shiftuser extends BaseEntity{
    private UUID id;
    private UUID shiftId;
    private UUID userId;
    private UUID tenantId;
}
