package com.nhom7.quanlytrampin.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Battery")
public class Battery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "status")
    private String status; // Available, InUse, Charging, Maintenance

    @Column(name = "condition")
    private String condition; // Good, Fair, Poor

    @Column(name = "stationId")
    private Long stationId;

    @Column(name = "lastUpdated")
    private LocalDateTime lastUpdated;

    // Constructors
    public Battery() {
        this.lastUpdated = LocalDateTime.now();
    }

   