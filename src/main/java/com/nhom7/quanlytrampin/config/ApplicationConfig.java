package com.nhom7.quanlytrampin.config;

import com.nhom7.quanlytrampin.repository.NhanVienRepository; 
import com.nhom7.quanlytrampin.repository.TaiXeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    private final TaiXeRepository taiXeRepository;
    private final NhanVienRepository nhanVienRepository; 

    public ApplicationConfig(TaiXeRepository taiXeRepository, NhanVienRepository nhanVienRepository) {
        this.taiXeRepository = taiXeRepository;
        this.nhanVienRepository = nhanVienRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            var taiXe = taiXeRepository.findByUsername(username);
            if (taiXe.isPresent()) {
                return taiXe.get();
            }

            var nhanVien = nhanVienRepository.findByUsername(username);
            if (nhanVien.isPresent()) {
                return nhanVien.get();
            }
         
            throw new UsernameNotFoundException("User not found: " + username);
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
