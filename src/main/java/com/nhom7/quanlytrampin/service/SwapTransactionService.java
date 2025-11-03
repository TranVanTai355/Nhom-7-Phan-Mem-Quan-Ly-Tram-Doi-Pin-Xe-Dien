package com.nhom7.quanlytrampin.service;

import com.nhom7.quanlytrampin.entity.Battery;
import com.nhom7.quanlytrampin.entity.SwapTransaction;
import com.nhom7.quanlytrampin.repository.BatteryRepository;
import com.nhom7.quanlytrampin.repository.SwapTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SwapTransactionService {

    @Autowired
    private SwapTransactionRepository swapRepo;

    @Autowired
    private BatteryRepository batteryRepo;

    public SwapTransaction confirmSwap(Long userId, Long oldBatteryId, Long newBatteryId) {
        Battery oldBattery = batteryRepo.findById(oldBatteryId).orElse(null);
        Battery newBattery = batteryRepo.findById(newBatteryId).orElse(null);

        SwapTransaction swap = new SwapTransaction();
        swap.setUserId(userId);
        swap.setOldBattery(oldBattery);
        swap.setNewBattery(newBattery);
        swap.setStatus("PENDING");

        return swapRepo.save(swap);
    }

    public SwapTransaction recordOnsitePayment(Long transactionId, double amount) {
        SwapTransaction swap = swapRepo.findById(transactionId).orElse(null);
        if (swap == null) return null;

        swap.setPaymentAmount(amount);
        swap.setStatus("PAID");

        return swapRepo.save(swap);
    }

    public SwapTransaction inspectReturnedBattery(Long transactionId, String condition) {
        SwapTransaction swap = swapRepo.findById(transactionId).orElse(null);
        if (swap == null) return null;

        swap.setReturnedBatteryCondition(condition);
        swap.setStatus("COMPLETED");

        return swapRepo.save(swap);
    }
}
