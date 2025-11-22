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

    private final ThanhToanRepository thanhToanRepository;
    private final TramPinRepository tramPinRepository;
    private final PinRepository pinRepository;
    private final TaiXeRepository taiXeRepository;
    private final NhanVienRepository nhanVienRepository;
    private final GoiThuePinRepository goiThuePinRepository;
    private final SupportRequestRepository supportRequestRepository;
    private final TransactionRepository transactionRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(
            ThanhToanRepository thanhToanRepository,
            TramPinRepository tramPinRepository,
            PinRepository pinRepository,
            TaiXeRepository taiXeRepository,
            NhanVienRepository nhanVienRepository,
            GoiThuePinRepository goiThuePinRepository,
            SupportRequestRepository supportRequestRepository,
            TransactionRepository transactionRepository,
            PasswordEncoder passwordEncoder) {

        this.thanhToanRepository = thanhToanRepository;
        this.tramPinRepository = tramPinRepository;
        this.pinRepository = pinRepository;
        this.taiXeRepository = taiXeRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.goiThuePinRepository = goiThuePinRepository;
        this.supportRequestRepository = supportRequestRepository;
        this.transactionRepository = transactionRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/stations")
    public List<TramPin> getAllStations() {
        return tramPinRepository.findAll();
    }

    @PostMapping("/stations")
    public TramPin createStation(@RequestBody TramPin tramPin) {
        return tramPinRepository.save(tramPin);
    }

    @PutMapping("/pins/{id}/dispatch/{stationId}")
    public ResponseEntity<Pin> dispatchBattery(
            @PathVariable Long id,
            @PathVariable Long stationId) {

        Pin pin = pinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pin không tồn tại"));

        TramPin tramPin = tramPinRepository.findById(stationId)
                .orElseThrow(() -> new RuntimeException("Trạm pin không tồn tại"));

        pin.setTramPin(tramPin);
        pin.setTrangThai("Sẵn sàng");

        return ResponseEntity.ok(pinRepository.save(pin));
    }

    @PutMapping("/requests/{id}/resolve")
    public ResponseEntity<SupportRequest> resolveSupportRequest(@PathVariable Long id) {
        SupportRequest request = supportRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Yêu cầu hỗ trợ không tồn tại"));

        request.setStatus("RESOLVED"); 

        return ResponseEntity.ok(supportRequestRepository.save(request));
    }


    @PostMapping("/staff")
    public NhanVien createStaff(@RequestBody NhanVien nhanVien) {

        String encodedPassword = passwordEncoder.encode(nhanVien.getPassword());
        nhanVien.setPassword(encodedPassword);

        return nhanVienRepository.save(nhanVien);
    }

    @GetMapping("/drivers")
    public List<TaiXe> getAllDrivers() {
        return taiXeRepository.findAll();
    }

    @PostMapping("/subscription-plans")
    public GoiThuePin createSubscriptionPlan(@RequestBody GoiThuePin goiThuePin) {
        return goiThuePinRepository.save(goiThuePin);
    }


    @GetMapping("/reports/total-revenue")
    public Double getTotalRevenue() {
        return transactionRepository.findAll()
                .stream()
                .filter(Transaction::isPaid) 
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @GetMapping("/reports/swap-count")
    public Long getTotalSwapCount() {
        return transactionRepository.count();
    }
}
