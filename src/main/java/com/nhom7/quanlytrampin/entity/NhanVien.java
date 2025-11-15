package com.nhom7.quanlytrampin.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "NhanVien")
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maNhanVien;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String hoTen;

    private String role; // Ví dụ: "STAFF", "ADMIN"

    @ManyToOne
    @JoinColumn(name = "MaTramPin") // Nhân viên này làm ở trạm nào
    private TramPin tramPin;

    // Getters and Setters
    public Long getMaNhanVien() {
        return maNhanVien;
    }
    public void setMaNhanVien(Long maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getHoTen() {
        return hoTen;
    }
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public TramPin getTramPin() {
        return tramPin;
    }
    public void setTramPin(TramPin tramPin) {
        this.tramPin = tramPin;
    }
}