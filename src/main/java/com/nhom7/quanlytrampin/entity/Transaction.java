package com.nhom7.quanlytrampin.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "old_battery_id")
    private Battery oldBattery;

    @ManyToOne
    @JoinColumn(name = "new_battery_id")
    private Battery newBattery;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "status")
    private String status; // Pending, Completed, Cancelled

    @Column(name = "payment_method")
    private String paymentMethod; // OnSite, Online, Subscription

    @Column(name = "amount")
    private double amount;

    @Column(name = "is_paid")
    private boolean isPaid;

   