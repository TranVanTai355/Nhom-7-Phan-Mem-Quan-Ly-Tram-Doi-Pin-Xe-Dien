package com.nhom7.quanlytrampin.service;

import com.nhom7.quanlytrampin.dto.PaymentRequestDto;
import com.nhom7.quanlytrampin.entity.GoiThuePin;
import com.nhom7.quanlytrampin.entity.TaiXe;
import com.nhom7.quanlytrampin.entity.ThanhToan;
import com.nhom7.quanlytrampin.repository.GoiThuePinRepository;
import com.nhom7.quanlytrampin.repository.TaiXeRepository;
import com.nhom7.quanlytrampin.repository.ThanhToanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private ThanhToanRepository thanhToanRepository;

    @Autowired
    private TaiXeRepository taiXeRepository;

    @Autowired
    private GoiThuePinRepository goiThuePinRepository;

    public ThanhToan createPayment(PaymentRequestDto request) {
        // 1. Tìm tài xế
        TaiXe taiXe = taiXeRepository.findById(request.getMaTaiXe())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài xế"));

       ThanhToan newPayment = new ThanhToan();
        newPayment.setTaiXe(taiXe);
        newPayment.setSoTien(request.getSoTien());
        newPayment.setNoiDung(request.getNoiDung());
        newPayment.setPhuongThuc(request.getPhuongThuc());
        newPayment.setNgayThanhToan(LocalDateTime.now()); 

        if (request.getMaGoi() != null) {
           GoiThuePin goi = goiThuePinRepository.findById(request.getMaGoi())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy gói thuê pin"));
            newPayment.setGoiThuePin(goi);
        }

        return thanhToanRepository.save(newPayment);
    }

    public List<ThanhToan> getPaymentHistory(Long taiXeId) {

        return thanhToanRepository.findByTaiXe_MaTaiXe(taiXeId);
    }
}