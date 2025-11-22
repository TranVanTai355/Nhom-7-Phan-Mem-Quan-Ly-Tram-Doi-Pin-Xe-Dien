package com.nhom7.quanlytrampin.service;

import com.nhom7.quanlytrampin.entity.TaiXe;
import com.nhom7.quanlytrampin.repository.TaiXeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder; // Import
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private TaiXeRepository taiXeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    public TaiXe registerDriver(TaiXe taiXeRequest) {
 
        if (taiXeRepository.findByUsername(taiXeRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username đã tồn tại!");
        }

        
        taiXeRequest.setMatKhau(passwordEncoder.encode(taiXeRequest.getMatKhau()));
        
       
        taiXeRequest.setRole("ROLE_DRIVER");

        
        return taiXeRepository.save(taiXeRequest);
    }


    public String loginUser(String username, String matKhau) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, matKhau)
        );


        TaiXe taiXe = taiXeRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài xế (lỗi lạ)"));


        return jwtService.generateToken(taiXe);
    }
}
