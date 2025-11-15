package com.nhom7.quanlytrampin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuanlytrampinApplication { // Đổi tên class cho đúng quy ước

    public static void main(String[] args) {
        SpringApplication.run(QuanlytrampinApplication.class, args); // Cập nhật tên class
        System.out.println("EV Battery Swap Station Management System started.");
    }

}