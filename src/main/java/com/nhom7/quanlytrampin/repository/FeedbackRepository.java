package com.nhom7.quanlytrampin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.nhom7.quanlytrampin.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<Feedback> findByStationIdOrderByCreatedAtDesc(Long stationId);
}
