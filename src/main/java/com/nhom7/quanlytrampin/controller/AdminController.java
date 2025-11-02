package com.nhom7.quanlytrampin.controller;

import com.nhom7.quanlytrampin.entity.ThanhToan;
import com.nhom7.quanlytrampin.repository.TramPinRepository;
import com.nhom7.quanlytrampin.repository.ThanhToanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private ThanhToanRepository thanhToanRepository;

    @Autowired
    private TramPinRepository tramPinRepository;

    @GetMapping("/report/summary")
    public ResponseEntity<?> summary() {
        List<ThanhToan> all = thanhToanRepository.findAll();
        double total = all.stream().mapToDouble(t -> t.getSoTien().doubleValue()).sum();
        long transactions = all.size();
        long stations = tramPinRepository.count();
        return ResponseEntity.ok(Map.of(
                "totalRevenue", total,
                "transactions", transactions,
                "stations", stations
        ));
    }
}
