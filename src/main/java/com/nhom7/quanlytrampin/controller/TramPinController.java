package com.nhom7.quanlytrampin.controller;

import com.nhom7.quanlytrampin.entity.LichHen;
import com.nhom7.quanlytrampin.entity.TramPin;
import com.nhom7.quanlytrampin.service.TramPinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/taixe/tram-pin")
public class TramPinController {
    @Autowired
    private TramPinService tramPinService;

    @GetMapping("/danh-sach")
    public ResponseEntity<List<TramPin>> getAllActiveStations() {
        List<TramPin> stations = tramPinService.findAllActiveStations();
        return ResponseEntity.ok(stations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TramPin> getStationDetails(@PathVariable Long id) {
        try {
            TramPin station = tramPinService.getStationDetails(id);
            return ResponseEntity.ok(station);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/pin-san-co")
    public ResponseEntity<?> getAvailablePinCount(@PathVariable Long id, @RequestParam String loaiPin) {
        try {
            long count = tramPinService.getAvailablePinCount(id, loaiPin);
            return ResponseEntity.ok(Map.of("soLuong", count));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/dat-lich")
    public ResponseEntity<?> bookAppointment(@RequestBody LichHen lichHen) {
        try {
            LichHen newBooking = tramPinService.createBooking(lichHen);
            return ResponseEntity.ok(newBooking);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
