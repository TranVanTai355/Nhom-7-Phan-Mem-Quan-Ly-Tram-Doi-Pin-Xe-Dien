package com.nhom7.quanlytrampin.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "TramPin")
public class TramPin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTramPin")
    private Long maTramPin;

    @Column(name = "TenTramPin", nullable = false)
    private String tenTramPin;

    @Column(name = "DiaChi", nullable = false)
    private String diaChi;

    @Column(name = "TrangThaiHoatDong")
    private Boolean trangThaiHoatDong;

    @OneToMany(mappedBy = "tramPin")
    @JsonIgnore
    private List<Pin> danhsachPin;

    @OneToMany(mappedBy = "tramPin")
    @JsonIgnore
    private List<LichHen> danhsachLichHen;



    public Long getMaTramPin() {
        return maTramPin;
    }

    public void setMaTramPin(Long maTramPin) {
        this.maTramPin = maTramPin;
    }

    public String getTenTramPin() {
        return tenTramPin;
    }

    public void setTenTramPin(String tenTramPin) {
        this.tenTramPin = tenTramPin;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Boolean getTrangThaiHoatDong() {
        return trangThaiHoatDong;
    }

    public void setTrangThaiHoatDong(Boolean trangThaiHoatDong) {
        this.trangThaiHoatDong = trangThaiHoatDong;
    }

    public List<Pin> getDanhsachPin() {
        return danhsachPin;
    }

    public void setDanhsachPin(List<Pin> danhsachPin) {
        this.danhsachPin = danhsachPin;
    }

    public List<LichHen> getDanhsachLichHen() {
        return danhsachLichHen;
    }

    public void setDanhsachLichHen(List<LichHen> danhsachLichHen) {
        this.danhsachLichHen = danhsachLichHen;
    }

}
