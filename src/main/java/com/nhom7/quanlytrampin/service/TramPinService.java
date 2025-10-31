package com.nhom7.quanlytrampin.service;

import com.nhom7.quanlytrampin.entity.LichHen;
import com.nhom7.quanlytrampin.entity.PhuongTien;
import com.nhom7.quanlytrampin.entity.TaiXe;
import com.nhom7.quanlytrampin.entity.TramPin;
import com.nhom7.quanlytrampin.repository.LichHenRepository;
import com.nhom7.quanlytrampin.repository.TaiXeRepository;
import com.nhom7.quanlytrampin.repository.TramPinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TramPinService {
    @Autowired
    private TramPinRepository tramPinRepository;

    @Autowired
    private LichHenRepository lichHenRepository;

    @Autowired
    private TaiXeRepository taiXeRepository;

    public List<TramPin> findAllActiveStations() {
        return tramPinRepository.findByTrangThaiHoatDong(true);
    }

    public TramPin getStationDetails(Long stationId) {
        Optional<TramPin> tramPin = tramPinRepository.findById(stationId);
        if (tramPin.isEmpty()) {
            throw new RuntimeException("Không tìm thấy trạm pin với ID: " + stationId);
        }
        return tramPin.get();
    }

    public long getAvailablePinCount(Long stationId, String loaiPin) {
        TramPin tramPin = getStationDetails(stationId);
        return tramPin.getDanhsachPin().stream()
                .filter(pin -> pin.getLoaiPin().equalsIgnoreCase(loaiPin) && "Sẵn sàng".equals(pin.getTrangThai()))
                .count();
    }

    public LichHen createBooking(LichHen bookingRequest) {
        TramPin tramPin = tramPinRepository.findById(bookingRequest.getTramPin().getMaTramPin())
                .orElseThrow(() -> new RuntimeException("Trạm pin không tồn tại."));

        TaiXe taiXe = taiXeRepository.findById(bookingRequest.getTaiXe().getMaTaiXe())
                .orElseThrow(() -> new RuntimeException("Tài xế không tồn tại."));

        String loaiPinCuaXe = taiXe.getDanhSachPhuongTien().stream()
                .map(PhuongTien::getLoaiPin)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin phương tiện hoặc loại pin của tài xế."));

        long availablePins = getAvailablePinCount(tramPin.getMaTramPin(), loaiPinCuaXe);
        if (availablePins <= 0) {
            throw new RuntimeException("Trạm đã hết loại pin phù hợp. Vui lòng chọn trạm khác.");
        }

        bookingRequest.setTramPin(tramPin);
        bookingRequest.setTaiXe(taiXe);
        bookingRequest.setTrangThai("Đã đặt");

        return lichHenRepository.save(bookingRequest);
    }
}
