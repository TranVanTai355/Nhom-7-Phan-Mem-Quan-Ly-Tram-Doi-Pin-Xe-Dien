package com.nhom7.quanlytrampin.controller;

import com.nhom7.quanlytrampin.entity.Transaction;
import com.nhom7.quanlytrampin.service.InventoryService;
import com.nhom7.quanlytrampin.service.BatterySwapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/staff")
public class NhanVienController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private BatterySwapService batterySwapService;

    @GetMapping("/inventory/track")
    public Map<String, Long> trackBatteries() {
        return inventoryService.trackBatteries();
    }

    @GetMapping("/inventory/categorize")
    public Map<String, List<?>> categorize() {
        return inventoryService.categorizeBatteries();
    }

    @PostMapping("/swap/confirm")
    public Transaction confirmSwap(@RequestParam Long oldBatteryId,
                                   @RequestParam Long newBatteryId,
                                   @RequestParam Long driverId) {
        return batterySwapService.confirmSwap(oldBatteryId, newBatteryId, driverId);
    }

    @PostMapping("/swap/payment")
    public String recordPayment(@RequestParam Long transactionId,
                                @RequestParam double amount) {
        batterySwapService.recordOnsitePayment(transactionId, amount);
        return "Payment recorded successfully.";
    }

    @PutMapping("/battery/inspect")
    public String inspectBattery(@RequestParam Long batteryId,
                                 @RequestParam String condition) {
        batterySwapService.inspectReturnedBattery(batteryId, condition);
        return "Battery inspection updated.";
    }
}
