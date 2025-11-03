package com.nhom7.quanlytrampin.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "swap_transaction")
public class SwapTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "old_battery_id")
    private Battery oldBattery;

    @ManyToOne
    @JoinColumn(name = "new_battery_id")
    private Battery newBattery;

    private double paymentAmount;

    private String status; // PENDING, COMPLETED, PAID

    private String returnedBatteryCondition;

    private LocalDateTime createdAt = LocalDateTime.now();
}
