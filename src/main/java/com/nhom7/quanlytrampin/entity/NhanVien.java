package com.nhom7.quanlytrampin.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore; 
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "NhanVien")
public class NhanVien implements UserDetails { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maNhanVien;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String hoTen;

    private String role;

    @ManyToOne
    @JoinColumn(name = "MaTramPin")
    @JsonIgnore 
    private TramPin tramPin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == null || this.role.trim().isEmpty()) {
            return List.of();
        }

        String cleanRole = this.role.trim().toUpperCase();
        return List.of(new SimpleGrantedAuthority(cleanRole)); 
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }

  
    public Long getMaNhanVien() { return maNhanVien; }
    public void setMaNhanVien(Long maNhanVien) { this.maNhanVien = maNhanVien; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public TramPin getTramPin() { return tramPin; }
    public void setTramPin(TramPin tramPin) { this.tramPin = tramPin; }
}
