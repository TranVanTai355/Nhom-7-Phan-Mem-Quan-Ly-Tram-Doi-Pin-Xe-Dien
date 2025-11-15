package com.nhom7.quanlytrampin.dto;

public class LoginRequestDTO {
    private String username;
    private String matKhau;

    // Thêm Getter và Setter cho 2 trường này
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getMatKhau() {
        return matKhau;
    }
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
