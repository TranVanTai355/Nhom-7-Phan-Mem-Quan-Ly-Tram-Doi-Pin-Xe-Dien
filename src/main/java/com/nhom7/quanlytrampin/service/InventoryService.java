package com.nhom7.quanlytrampin.service;

// Sửa Battery -> Pin
import com.nhom7.quanlytrampin.entity.Pin; 
// Sửa BatteryRepository -> PinRepository
import com.nhom7.quanlytrampin.repository.PinRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private PinRepository pinRepository; // Sửa tên Repository

    public Map<String, Long> trackBatteries() {
        List<Pin> pins = pinRepository.findAll(); // Sửa tên biến và repo
        return pins.stream()
                .collect(Collectors.groupingBy(Pin::getTrangThai, Collectors.counting())); // Dùng Pin::getTrangThai
    }

    // Sửa kiểu trả về Battery -> Pin
    public Map<String, List<Pin>> categorizeBatteries() { 
        List<Pin> pins = pinRepository.findAll(); // Sửa tên biến và repo
        return pins.stream()
                .collect(Collectors.groupingBy(p -> p.getModel() + "_" + p.getDungLuong() + "_" + p.getTrangThai())); // Dùng các getter của Pin
    }
}