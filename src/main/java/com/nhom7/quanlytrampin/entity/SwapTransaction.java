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

        // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Battery getOldBattery() {
        return oldBattery;
    }
    public void setOldBattery(Battery oldBattery) {
        this.oldBattery = oldBattery;
    }

    public Battery getNewBattery() {
        return newBattery;
    }
    public void setNewBattery(Battery newBattery) {
        this.newBattery = newBattery;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }
    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getReturnedBatteryCondition() {
        return returnedBatteryCondition;
    }
    public void setReturnedBatteryCondition(String returnedBatteryCondition) {
        this.returnedBatteryCondition = returnedBatteryCondition;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
