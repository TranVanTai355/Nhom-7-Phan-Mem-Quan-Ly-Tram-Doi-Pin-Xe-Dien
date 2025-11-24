package com.nhom7.quanlytrampin.controller;

import com.nhom7.quanlytrampin.entity.*;
import com.nhom7.quanlytrampin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired private TramPinRepository tramPinRepository;
    @Autowired private PinRepository pinRepository;
    @Autowired private TaiXeRepository taiXeRepository;
    @Autowired private NhanVienRepository nhanVienRepository; 
    @Autowired private TransactionRepository transactionRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/staff")
    public NhanVien createStaff(@RequestBody NhanVien nhanVien) {
        if (nhanVienRepository.findByUsername(nhanVien.getUsername()).isPresent()) {
            throw new RuntimeException("Username đã tồn tại");
        }
        nhanVien.setPassword(passwordEncoder.encode(nhanVien.getPassword()));
        nhanVien.setRole("ROLE_STAFF"); 
        return nhanVienRepository.save(nhanVien);
    }

    @GetMapping("/staff-list")
    public List<NhanVien> getAllStaff() {
        return nhanVienRepository.findAll();
    }

    @GetMapping("/drivers")
    public List<TaiXe> getAllDrivers() {
        return taiXeRepository.findAll();
    }

    @GetMapping("/stations")
    public List<TramPin> getAllStations() { return tramPinRepository.findAll(); }

    @PostMapping("/stations")
    public TramPin createStation(@RequestBody TramPin tramPin) {
        if(tramPin.getTrangThaiHoatDong() == null) tramPin.setTrangThaiHoatDong(true);
        return tramPinRepository.save(tramPin);
    }
    
    @DeleteMapping("/stations/{id}")
    public ResponseEntity<?> deleteStation(@PathVariable Long id) {
        tramPinRepository.deleteById(id);
        return ResponseEntity.ok("Đã xóa trạm thành công");
    }
}
