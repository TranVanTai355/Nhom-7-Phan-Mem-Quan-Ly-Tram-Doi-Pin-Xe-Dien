package com.nhom7.quanlytrampin.repository;

import com.nhom7.quanlytrampin.entity.ThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ThanhToanRepository extends JpaRepository<ThanhToan, Long> {
    List<ThanhToan> findByTaiXe_MaTaiXe(Long maTaiXe);
}