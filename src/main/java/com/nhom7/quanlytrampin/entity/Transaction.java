package com.nhom7.quanlytrampin.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.nhom7.quanlytrampin.entity.Pin; 

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "old_battery_id")
    private Pin oldBattery; 

    @ManyToOne
    @JoinColumn(name = "new_battery_id")
    private Pin newBattery; 

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "status")
    private String status; 

    @Column(name = "payment_method")
    private String paymentMethod; 

    @Column(name = "amount")
    private double amount;

    @Column(name = "is_paid")
    private boolean isPaid;

    
    public Transaction() {
        this.timestamp = LocalDateTime.now();
        this.isPaid = false;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public Pin getOldBattery() {
        return oldBattery;
    }

    
    public void setOldBattery(Pin oldBattery) {
        this.oldBattery = oldBattery;
    }

    
    public Pin getNewBattery() {
        return newBattery;
    }

    
    public void setNewBattery(Pin newBattery) {
        this.newBattery = newBattery;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
