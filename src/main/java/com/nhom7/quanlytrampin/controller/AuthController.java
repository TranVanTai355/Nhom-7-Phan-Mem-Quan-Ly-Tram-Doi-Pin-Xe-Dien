package com.nhom7.quanlytrampin.controller;

import com.nhom7.quanlytrampin.dto.LoginRequestDTO;
import com.nhom7.quanlytrampin.entity.TaiXe;
import com.nhom7.quanlytrampin.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerDriver(@RequestBody TaiXe taiXe) {
        try {
            TaiXe newDriver = authService.registerDriver(taiXe);
            return ResponseEntity.ok(newDriver);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            String token = authService.loginUser(
                    loginRequest.getUsername(),
                    loginRequest.getMatKhau()
            );

            return ResponseEntity.ok(Map.of("token", token));

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Tài khoản hoặc mật khẩu không đúng!");
        }
    }
}
