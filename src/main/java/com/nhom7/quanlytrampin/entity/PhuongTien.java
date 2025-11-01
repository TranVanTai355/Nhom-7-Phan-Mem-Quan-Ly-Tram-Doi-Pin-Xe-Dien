package com.nhom7.quanlytrampin.entity;

import jakarta.persistence.*;

@Entity
public class PhuongTien { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maXe; 

    private String loaiXe; 
    private String loaiPin;

    @ManyToOne
    @JoinColumn(name = "ma_tai_xe", nullable = false) 
    private TaiXe taiXe;

    public Long getMaXe() {
        return maXe;
    }

    public void setMaXe(Long maXe) {
        this.maXe = maXe;
    }

    public String getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(String loaiXe) {
        this.loaiXe = loaiXe;
    }

    public String getLoaiPin() {
        return loaiPin;
    }

    public void setLoaiPin(String loaiPin) {
        this.loaiPin = loaiPin;
    }

    public TaiXe getTaiXe() {
        return taiXe;
    }

    public void setTaiXe(TaiXe taiXe) {
        this.taiXe = taiXe;
    }

}