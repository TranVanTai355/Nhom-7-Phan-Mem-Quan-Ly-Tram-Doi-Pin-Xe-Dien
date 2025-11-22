package com.nhom7.quanlytrampin.controller;

import com.nhom7.quanlytrampin.entity.Pin; 
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

    private final InventoryService inventoryService;
    private final BatterySwapService batterySwapService;

    @Autowired
    public NhanVienController(InventoryService inventoryService, BatterySwapService batterySwapService) {
        this.inventoryService = inventoryService;
        this.batterySwapService = batterySwapService;
    }

    @GetMapping("/inventory/track")
    public Map<String, Long> trackBatteries() {
        return inventoryService.trackBatteries();
    }

    @GetMapping("/inventory/categorize")
    // Sửa kiểu trả về Battery -> Pin
    public Map<String, List<Pin>> categorize() { 
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
