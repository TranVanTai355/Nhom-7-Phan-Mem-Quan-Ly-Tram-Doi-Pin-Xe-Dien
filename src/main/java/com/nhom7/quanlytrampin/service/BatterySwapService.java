package com.nhom7.quanlytrampin.service;

import com.nhom7.quanlytrampin.entity.*;
import com.nhom7.quanlytrampin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class BatterySwapService {

    @Autowired
    private BatteryRepository batteryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction confirmSwap(Long oldBatteryId, Long newBatteryId, Long driverId) {
        Battery oldBattery = batteryRepository.findById(oldBatteryId).orElseThrow();
        Battery newBattery = batteryRepository.findById(newBatteryId).orElseThrow();

        oldBattery.setStatus("Charging");
        newBattery.setStatus("InUse");
        batteryRepository.save(oldBattery);
        batteryRepository.save(newBattery);

        Transaction tx = new Transaction();
        tx.setOldBattery(oldBattery);
        tx.setNewBattery(newBattery);
        tx.setDriverId(driverId);
        tx.setTimestamp(LocalDateTime.now());
        tx.setStatus("Completed");

        return transactionRepository.save(tx);
    }

    public void recordOnsitePayment(Long transactionId, double amount) {
        Transaction tx = transactionRepository.findById(transactionId).orElseThrow();
        tx.setPaymentMethod("OnSite");
        tx.setAmount(amount);
        tx.setPaid(true);
        transactionRepository.save(tx);
    }

    public void inspectReturnedBattery(Long batteryId, String condition) {
        Battery b = batteryRepository.findById(batteryId).orElseThrow();
        b.setCondition(condition);
        b.setStatus(condition.equals("Good") ? "Charged" : "Maintenance");
        batteryRepository.save(b);
    }
}
