package com.nhom7.quanlytrampin.config; // 1. Đảm bảo đúng package của bạn

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 2. Báo cho Spring biết đây là file cấu hình
@EnableWebSecurity // 3. Bật tính năng Spring Security
public class SecurityConfig {

    // 4. Tạo ra một Bean PasswordEncoder để mã hóa mật khẩu
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 5. Đây là nơi định nghĩa các quy tắc bảo mật
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Tạm thời tắt CSRF (Cross-Site Request Forgery) để dễ test API
            .authorizeHttpRequests(auth -> auth
                // 6. Định nghĩa các đường dẫn (URL) không cần xác thực
                .requestMatchers(
                    "/api/auth/**",     // Ví dụ: cho các API đăng nhập, đăng ký
                    "/public/**",         // Ví dụ: cho các tài nguyên công khai
                    "/swagger-ui/**",     // Cho phép truy cập Swagger UI
                    "/v3/api-docs/**"   // Cho phép truy cập tài liệu API (JSON)
                ).permitAll()

                // 7. Tất cả các request còn lại ĐỀU PHẢI được xác thực (phải login)
                .anyRequest().authenticated()
            );

        return http.build();
    }
}