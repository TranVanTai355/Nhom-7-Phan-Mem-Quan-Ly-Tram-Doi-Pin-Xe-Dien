package com.nhom7.quanlytrampin.controller;

import com.nhom7.quanlytrampin.service.InventoryService;
import com.nhom7.quanlytrampin.service.SwapTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/battery")
public class BatteryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private SwapTransactionService swapService;

    // 📌 Theo Part 2.a (tracking + categorize)
    @GetMapping("/track")
    public Map<String, Long> trackBatteries() {
        return inventoryService.trackBatteries();
    }

    @GetMapping("/categorize")
    public Map<String, ?> categorizeBatteries() {
        return inventoryService.categorizeBatteries();
    }

    // 📌 Theo Part 2.b (swap transaction)
    @PostMapping("/confirm-swap")
    public String confirmSwap(@RequestParam Long userId,
                              @RequestParam Long oldBattery,
                              @RequestParam Long newBattery) {
        return swapService.confirmSwap(userId, oldBattery, newBattery);
    }

    @PostMapping("/payment")
    public String onsitePayment(@RequestParam Long transId,
                                @RequestParam double amount) {
        return swapService.recordOnsitePayment(transId, amount);
    }

    @PostMapping("/inspect")
    public String inspectBattery(@RequestParam Long batteryId,
                                 @RequestParam String condition) {
        return swapService.inspectReturnedBattery(batteryId, condition);
    }
}
