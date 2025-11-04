package com.nhom7.quanlytrampin.repository;

import com.nhom7.quanlytrampin.entity.PhuongTien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhuongTienRepository extends JpaRepository<PhuongTien, Long> {
    
    List<PhuongTien> findByTaiXe_MaTaiXe(Long maTaiXe);
    
    List<PhuongTien> findByLoaiXe(String loaiXe);
    
    List<PhuongTien> findByLoaiPin(String loaiPin);
}