package com.nhom7.quanlytrampin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.nhom7.quanlytrampin.entity.SupportRequest;

public interface SupportRequestRepository extends JpaRepository<SupportRequest, Long> {
    List<SupportRequest> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<SupportRequest> findByStationIdOrderByCreatedAtDesc(Long stationId);
    List<SupportRequest> findByStatus(String status);
}
