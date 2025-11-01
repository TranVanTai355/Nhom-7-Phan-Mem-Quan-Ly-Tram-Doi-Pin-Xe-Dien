package com.nhom7.quanlytrampin.controller;

import com.nhom7.quanlytrampin.dto.PaymentRequestDto;
import com.nhom7.quanlytrampin.entity.ThanhToan;
import com.nhom7.quanlytrampin.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") 
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping("/payments")
    public ResponseEntity<?> createPayment(@RequestBody PaymentRequestDto request) {
        try {
            ThanhToan newPayment = paymentService.createPayment(request);
            return ResponseEntity.ok(newPayment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/drivers/{driverId}/payments")
    public ResponseEntity<List<ThanhToan>> getPaymentHistory(@PathVariable Long driverId) {
        List<ThanhToan> history = paymentService.getPaymentHistory(driverId);
        return ResponseEntity.ok(history);
    }
}