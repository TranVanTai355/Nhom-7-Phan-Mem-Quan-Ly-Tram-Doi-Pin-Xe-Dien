package com.nhom7.quanlytrampin.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class ThanhToan { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maThanhToan; 

    @Column(nullable = false)
    private BigDecimal soTien; 

    @Column(nullable = false)
    private LocalDateTime ngayThanhToan; 

    private String phuongThuc;
    private String noiDung;

    @ManyToOne
    @JoinColumn(name = "ma_tai_xe", nullable = false)
    private TaiXe taiXe;

    @ManyToOne
    @JoinColumn(name = "ma_goi", nullable = true)
    private GoiThuePin goiThuePin;

    public Long getMaThanhToan() {
        return maThanhToan;
    }

    public void setMaThanhToan(Long maThanhToan) {
        this.maThanhToan = maThanhToan;
    }

    public BigDecimal getSoTien() {
        return soTien;
    }

    public void setSoTien(BigDecimal soTien) {
        this.soTien = soTien;
    }

    public LocalDateTime getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(LocalDateTime ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getPhuongThuc() {
        return phuongThuc;
    }

    public void setPhuongThuc(String phuongThuc) {
        this.phuongThuc = phuongThuc;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public TaiXe getTaiXe() {
        return taiXe;
    }

    public void setTaiXe(TaiXe taiXe) {
        this.taiXe = taiXe;
    }

    public GoiThuePin getGoiThuePin() {
        return goiThuePin;
    }

    public void setGoiThuePin(GoiThuePin goiThuePin) {
        this.goiThuePin = goiThuePin;
    }
    
}