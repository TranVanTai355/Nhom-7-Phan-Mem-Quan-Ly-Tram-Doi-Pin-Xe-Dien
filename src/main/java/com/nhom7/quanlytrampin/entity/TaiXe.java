package com.nhom7.quanlytrampin.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "TaiXe")
public class TaiXe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTaiXe")
    private Long maTaiXe;

    @Column(name = "HoTen", nullable = false)
    private String hoTen;

    @Column(name = "SoDienThoai", unique = true, nullable = false)
    private String soDienThoai;

    @Column(name = "Email", unique = true)
    private String email;

    @Column(name = "MatKhau", nullable = false)
    private String matKhau; 

    @OneToMany(mappedBy = "taiXe")
    private List<PhuongTien> danhSachPhuongTien;


    public Long getMaTaiXe() {
        return maTaiXe;
    }

    public void setMaTaiXe(Long maTaiXe) {
        this.maTaiXe = maTaiXe;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public List<PhuongTien> getDanhSachPhuongTien() {
        return danhSachPhuongTien;
    }

    public void setDanhSachPhuongTien(List<PhuongTien> danhSachPhuongTien) {
        this.danhSachPhuongTien = danhSachPhuongTien;
    }
}