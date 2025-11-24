package com.nhom7.quanlytrampin.config;

import com.nhom7.quanlytrampin.entity.NhanVien;
import com.nhom7.quanlytrampin.repository.NhanVienRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatabaseSeeder {

    @Bean
    public CommandLineRunner initDatabase(NhanVienRepository nhanVienRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (nhanVienRepository.findByUsername("admin").isEmpty()) {
                
                NhanVien admin = new NhanVien();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("227205")); 
                admin.setHoTen("Super Admin");
                admin.setRole("ROLE_ADMIN");
                
                nhanVienRepository.save(admin);
                
                System.out.println(">>> ĐÃ TẠO TÀI KHOẢN ADMIN MẶC ĐỊNH");
            }
        };
    }
}
