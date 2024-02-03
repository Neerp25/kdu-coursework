package com.example.JPAhands.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "shift_types")
public class ShiftType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "unique_name")
    private String uniqueName;

    private String description;

    private boolean active;

    @Column(name = "time_zone")
    private String timeZone;

    @ManyToOne(fetch = FetchType.EAGER)
    private Tanant tenant;
}
