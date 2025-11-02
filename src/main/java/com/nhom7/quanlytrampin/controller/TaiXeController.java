package com.nhom7.quanlytrampin.controller;

import com.nhom7.quanlytrampin.entity.PhuongTien;
import com.nhom7.quanlytrampin.entity.TaiXe;
import com.nhom7.quanlytrampin.repository.PhuongTienRepository;
import com.nhom7.quanlytrampin.repository.TaiXeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Driver endpoints: profile, add vehicle
 */
@RestController
@RequestMapping("/api/taixe")
public class TaiXeController {

    @Autowired
    private TaiXeRepository taiXeRepository;

    @Autowired
    private PhuongTienRepository phuongTienRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getDriver(@PathVariable Long id) {
        return taiXeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateDriver(@PathVariable Long id, @RequestBody TaiXe in) {
        return taiXeRepository.findById(id).map(t -> {
            if (in.getHoTen() != null) t.setHoTen(in.getHoTen());
            if (in.getSoDienThoai() != null) t.setSoDienThoai(in.getSoDienThoai());
            if (in.getEmail() != null) t.setEmail(in.getEmail());
            taiXeRepository.save(t);
            return ResponseEntity.ok(t);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/vehicles")
    public ResponseEntity<?> addVehicle(@PathVariable Long id, @RequestBody PhuongTien vehicle) {
        return taiXeRepository.findById(id).map(t -> {
            vehicle.setTaiXe(t);
            PhuongTien saved = phuongTienRepository.save(vehicle);
            return ResponseEntity.ok(saved);
        }).orElse(ResponseEntity.notFound().build());
    }
}
