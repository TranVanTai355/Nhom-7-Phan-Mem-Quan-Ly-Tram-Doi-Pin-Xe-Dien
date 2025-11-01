package com.nhom7.quanlytrampin.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class GoiThuePin { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maGoi; 

    @Column(nullable = false)
    private String tenGoi; 

    private String moTa;
    
    @Column(nullable = false)
    private BigDecimal gia;

    private int thoiHan;

    public Long getMaGoi() {
        return maGoi;
    }

    public void setMaGoi(Long maGoi) {
        this.maGoi = maGoi;
    }

    public String getTenGoi() {
        return tenGoi;
    }

    public void setTenGoi(String tenGoi) {
        this.tenGoi = tenGoi;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public int getThoiHan() {
        return thoiHan;
    }

    public void setThoiHan(int thoiHan) {
        this.thoiHan = thoiHan;
    }
    
 
}