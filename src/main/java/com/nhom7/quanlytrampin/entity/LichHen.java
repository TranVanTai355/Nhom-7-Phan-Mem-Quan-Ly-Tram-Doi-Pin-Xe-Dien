package com.nhom7.quanlytrampin.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LichHen")
public class LichHen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLichHen")
    private Long maLichHen;

    @ManyToOne
    @JoinColumn(name = "MaTaiXe", nullable = false)
    private TaiXe taiXe;

    @ManyToOne
    @JoinColumn(name = "MaTramPin", nullable = false)
    private TramPin tramPin;

    @Column(name = "ThoiGianHen", nullable = false)
    private LocalDateTime thoiGianHen;

    @Column(name = "TrangThai")
    private String trangThai;

    public Long getMaLichHen() {
        return maLichHen;
    }
    public void setMaLichHen(Long maLichHen) {
        this.maLichHen = maLichHen;
    }

    public TaiXe getTaiXe() {
        return taiXe;
    }
    public void setTaiXe(TaiXe taiXe) {
        this.taiXe = taiXe;
    }

    public TramPin getTramPin() {
        return tramPin;
    }
    public void setTramPin(TramPin tramPin) {
        this.tramPin = tramPin;
    }

    public LocalDateTime getThoiGianHen() {
        return thoiGianHen;
    }
    public void setThoiGianHen(LocalDateTime thoiGianHen) {
        this.thoiGianHen = thoiGianHen;
    }

    public String getTrangThai() {
        return trangThai;
    }
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
