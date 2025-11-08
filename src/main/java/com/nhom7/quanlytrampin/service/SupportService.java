package com.nhom7.quanlytrampin.service;

import com.nhom7.quanlytrampin.dto.FeedbackDto;
import com.nhom7.quanlytrampin.dto.SupportRequestDto;
import com.nhom7.quanlytrampin.entity.Feedback;
import com.nhom7.quanlytrampin.entity.SupportRequest;
import com.nhom7.quanlytrampin.repository.FeedbackRepository;
import com.nhom7.quanlytrampin.repository.SupportRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SupportService {
    
    private final SupportRequestRepository supportRepo;
    private final FeedbackRepository feedbackRepo;

    public SupportService(SupportRequestRepository supportRepo, FeedbackRepository feedbackRepo) {
        this.supportRepo = supportRepo;
        this.feedbackRepo = feedbackRepo;
    }

    @Transactional
    public SupportRequest createSupportRequest(SupportRequestDto dto) {
        SupportRequest s = new SupportRequest();
        s.setUserId(dto.getUserId());
        s.setVehicleVin(dto.getVehicleVin());
        s.setStationId(dto.getStationId());
        s.setSubject(dto.getSubject());
        s.setDescription(dto.getDescription());
        s.setStatus("NEW");
        return supportRepo.save(s);
    }

    public List<SupportRequest> getSupportRequestsForUser(Long userId) {
        return supportRepo.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public Optional<SupportRequest> getSupportRequestById(Long id) {
        return supportRepo.findById(id);
    }

    @Transactional
    public SupportRequest updateSupportStatus(Long id, String status, String staffResponse) {
        SupportRequest req = supportRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Support request not found: " + id));
        req.setStatus(status);
        if (staffResponse != null) req.setStaffResponse(staffResponse);
        return supportRepo.save(req);
    }

    @Transactional
    public Feedback submitFeedback(FeedbackDto dto) {
        if (dto.getRating() == null || dto.getRating() < 1 || dto.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        Feedback fb = new Feedback();
        fb.setUserId(dto.getUserId());
        fb.setStationId(dto.getStationId());
        fb.setRating(dto.getRating());
        fb.setComment(dto.getComment());
        return feedbackRepo.save(fb);
    }

    public List<Feedback> getFeedbacksForUser(Long userId) {
        return feedbackRepo.findByUserIdOrderByCreatedAtDesc(userId);
    }
}
