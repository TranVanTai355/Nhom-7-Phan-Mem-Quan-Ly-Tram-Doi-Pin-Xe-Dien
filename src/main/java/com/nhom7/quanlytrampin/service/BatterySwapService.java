package com.nhom7.quanlytrampin.service;

import com.nhom7.quanlytrampin.entity.*;
// Thêm import
import com.nhom7.quanlytrampin.entity.Pin;
import com.nhom7.quanlytrampin.repository.*;
// Sửa BatteryRepository -> PinRepository
import com.nhom7.quanlytrampin.repository.PinRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class BatterySwapService {

    @Autowired
    private PinRepository pinRepository; // Sửa

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction confirmSwap(Long oldBatteryId, Long newBatteryId, Long driverId) {
        // Sửa Battery -> Pin
        Pin oldBattery = pinRepository.findById(oldBatteryId).orElseThrow(() -> new RuntimeException("Không tìm thấy pin cũ"));
        Pin newBattery = pinRepository.findById(newBatteryId).orElseThrow(() -> new RuntimeException("Không tìm thấy pin mới"));

        // Dùng trạng thái Tiếng Việt cho đồng bộ
        oldBattery.setTrangThai("Đang sạc"); 
        newBattery.setTrangThai("Đang sử dụng"); 
        pinRepository.save(oldBattery); // Sửa repo
        pinRepository.save(newBattery); // Sửa repo

        Transaction tx = new Transaction();
        tx.setOldBattery(oldBattery);
        tx.setNewBattery(newBattery);
        tx.setDriverId(driverId);
        tx.setTimestamp(LocalDateTime.now());
        tx.setStatus("Completed");

        return transactionRepository.save(tx);
    }

    public void recordOnsitePayment(Long transactionId, double amount) {
        Transaction tx = transactionRepository.findById(transactionId).orElseThrow(() -> new RuntimeException("Không tìm thấy giao dịch"));
        tx.setPaymentMethod("OnSite");
        tx.setAmount(amount);
        tx.setPaid(true);
        transactionRepository.save(tx);
    }

    public void inspectReturnedBattery(Long batteryId, String condition) {
        // Sửa Battery -> Pin
        Pin p = pinRepository.findById(batteryId).orElseThrow(() -> new RuntimeException("Không tìm thấy pin"));
        
        p.setCondition(condition); // Dùng trường "condition" ta vừa thêm
        
        // Dùng trạng thái Tiếng Việt (Giả định "Good" là "Sẵn sàng")
        p.setTrangThai(condition.equalsIgnoreCase("Good") ? "Sẵn sàng" : "Bảo trì");
        
        pinRepository.save(p); // Sửa repo
    }
}