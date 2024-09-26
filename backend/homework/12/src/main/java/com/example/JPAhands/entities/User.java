package com.example.JPAhands.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String username;

    @Column(name = "logged_in")
    private int loggedIn;

    @Column(name = "time_zone")
    private String timeZone;

    @ManyToOne(fetch = FetchType.EAGER)
    private Tanant tenant;
}
