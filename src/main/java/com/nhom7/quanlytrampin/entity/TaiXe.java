package com.nhom7.quanlytrampin.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name= "tai_xe")
public class TaiXe implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maTaiXe;

    @Column(nullable = false)
    private String hoTen;

    @Column(unique = true, nullable = false)
    private String soDienThoai;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String matKhau;

    @Column(nullable = false)
    private String role; 

    @Column(unique = true, nullable = false)
    private String username;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getPassword() {      
        return this.matKhau;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; 
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; 
    }

    @Override
    public boolean isEnabled() {
        return true; 
    }
}
