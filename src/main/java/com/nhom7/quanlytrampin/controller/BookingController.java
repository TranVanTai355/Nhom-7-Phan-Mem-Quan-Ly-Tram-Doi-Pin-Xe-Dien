package com.nhom7.quanlytrampin.controller;

import com.nhom7.quanlytrampin.entity.LichHen;
import com.nhom7.quanlytrampin.entity.TaiXe;
import com.nhom7.quanlytrampin.entity.TramPin;
import com.nhom7.quanlytrampin.repository.LichHenRepository;
import com.nhom7.quanlytrampin.repository.TaiXeRepository;
import com.nhom7.quanlytrampin.repository.TramPinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/taixe/bookings")
public class BookingController {

    @Autowired
    private LichHenRepository lichHenRepository;

    @Autowired
    private TaiXeRepository taiXeRepository;

    @Autowired
    private TramPinRepository tramPinRepository;


    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Map<String, Object> payload) {
        try {

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            TaiXe taiXe = taiXeRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy tài xế! Vui lòng đăng nhập lại."));

            if (payload.get("maTramPin") == null || payload.get("thoiGian") == null) {
                return ResponseEntity.badRequest().body("Thiếu thông tin 'maTramPin' hoặc 'thoiGian'");
            }

            Long maTramPin = Long.valueOf(payload.get("maTramPin").toString());
            String thoiGianStr = payload.get("thoiGian").toString();

            TramPin tramPin = tramPinRepository.findById(maTramPin)
                    .orElseThrow(() -> new RuntimeException("Trạm pin không tồn tại (ID: " + maTramPin + ")"));

            LichHen lichHen = new LichHen();
            lichHen.setTaiXe(taiXe);
            lichHen.setTramPin(tramPin);
            lichHen.setThoiGianHen(LocalDateTime.parse(thoiGianStr));
            lichHen.setTrangThai("CHO_XU_LY"); 
            LichHen savedBooking = lichHenRepository.save(lichHen);
            return ResponseEntity.ok(savedBooking);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi tạo lịch hẹn: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getMyBookings() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            
            TaiXe taiXe = taiXeRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User không tồn tại"));

            List<LichHen> listLichHen = lichHenRepository.findByTaiXe_MaTaiXe(taiXe.getMaTaiXe());
            
            return ResponseEntity.ok(listLichHen);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi tải danh sách: " + e.getMessage());
        }
    }
}
