package com.nhom7.quanlytrampin.service;

import com.nhom7.quanlytrampin.entity.TaiXe;
import com.nhom7.quanlytrampin.repository.TaiXeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private TaiXeRepository taiXeRepository;

    public TaiXe registerDriver(TaiXe taiXeRequest) {
        if(taiXeRepository.findByEmail(taiXeRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại!");
        }

        return taiXeRepository.save(taiXeRequest);
    }
}