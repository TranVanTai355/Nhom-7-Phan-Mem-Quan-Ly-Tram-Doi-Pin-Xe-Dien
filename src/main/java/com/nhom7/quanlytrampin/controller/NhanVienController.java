package com.nhom7.quanlytrampin.controller;

import com.nhom7.quanlytrampin.entity.*;
import com.nhom7.quanlytrampin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/staff")
public class NhanVienController {

    @Autowired private PinRepository pinRepository;
    @Autowired private TransactionRepository transactionRepository;
    @Autowired private TaiXeRepository taiXeRepository;
    @Autowired private LichHenRepository lichHenRepository; 

    @GetMapping("/appointments")
    public ResponseEntity<List<LichHen>> getAppointments(@RequestParam Long stationId) {
        return ResponseEntity.ok(lichHenRepository.findByTramPin_MaTramPin(stationId));
    }

    @GetMapping("/inventory/track")
    public Map<String, Long> trackBatteries() {
        List<Pin> allPins = pinRepository.findAll();
        return allPins.stream().collect(Collectors.groupingBy(Pin::getTrangThai, Collectors.counting()));
    }

    @PostMapping("/swap/confirm")
    public ResponseEntity<?> confirmSwap(@RequestParam Long oldBatteryId,
                                         @RequestParam Long newBatteryId,
                                         @RequestParam Long driverId) {
        try {
            Pin oldPin = pinRepository.findById(oldBatteryId).orElseThrow();
            Pin newPin = pinRepository.findById(newBatteryId).orElseThrow();
            

            oldPin.setTrangThai("DANG_SAC");
            newPin.setTrangThai("DA_GIAO");
            pinRepository.save(oldPin);
            pinRepository.save(newPin);

       
            Transaction trans = new Transaction();
            trans.setOldBattery(oldPin);
            trans.setNewBattery(newPin);
            trans.setDriverId(driverId);
            trans.setStatus("COMPLETED");
            return ResponseEntity.ok(transactionRepository.save(trans));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi: " + e.getMessage());
        }
    }
}
