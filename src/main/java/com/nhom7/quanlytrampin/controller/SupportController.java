package com.nhom7.quanlytrampin.controller;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.nhom7.quanlytrampin.dto.SupportResponseDto;
import com.nhom7.quanlytrampin.entity.Feedback;
import com.nhom7.quanlytrampin.entity.SupportRequest;
import com.nhom7.quanlytrampin.service.SupportService;
import com.nhom7.quanlytrampin.dto.SupportRequestDto;
import com.nhom7.quanlytrampin.dto.FeedbackDto;

import java.util.List;


@RestController
@RequestMapping("/api")
public class SupportController {
    
    private final SupportService supportService;

    @Autowired 
    public SupportController(SupportService supportService) {
        this.supportService = supportService;
    }

    @PostMapping("/support")
    public ResponseEntity<SupportResponseDto> createSupport(@RequestBody SupportRequestDto dto) {
        SupportRequest s = supportService.createSupportRequest(dto);
        return ResponseEntity.ok(toDto(s));
    }

    @GetMapping("/support/{userId}")
    public ResponseEntity<List<SupportResponseDto>> getSupports(@PathVariable Long userId) {
        List<SupportResponseDto> dtos = supportService.getSupportRequestsForUser(userId)
                .stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/support/detail/{id}")
    public ResponseEntity<SupportResponseDto> getSupportDetail(@PathVariable Long id) {
        return supportService.getSupportRequestById(id)
                .map(s -> ResponseEntity.ok(toDto(s)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/support/{id}/status")
    public ResponseEntity<SupportResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestParam String status,
            @RequestParam(required = false) String staffResponse) {
        SupportRequest updated = supportService.updateSupportStatus(id, status, staffResponse);
        return ResponseEntity.ok(toDto(updated));
    }

    @PostMapping("/feedback")
    public ResponseEntity<Feedback> submitFeedback(@RequestBody FeedbackDto dto) {
        Feedback f = supportService.submitFeedback(dto);
        return ResponseEntity.ok(f);
    }

    @GetMapping("/feedback/{userId}")
    public ResponseEntity<List<Feedback>> getFeedbacks(@PathVariable Long userId) {
        return ResponseEntity.ok(supportService.getFeedbacksForUser(userId));
    }

    private SupportResponseDto toDto(SupportRequest s) {
        SupportResponseDto dto = new SupportResponseDto();
        dto.setId(s.getId());
        dto.setUserId(s.getUserId());
        dto.setVehicleVin(s.getVehicleVin());
        dto.setStationId(s.getStationId());
        dto.setSubject(s.getSubject());
        dto.setDescription(s.getDescription());
        dto.setStatus(s.getStatus());
        dto.setStaffResponse(s.getStaffResponse());
        dto.setCreatedAt(s.getCreatedAt());
        dto.setUpdatedAt(s.getUpdatedAt());
        return dto;
    }
}
