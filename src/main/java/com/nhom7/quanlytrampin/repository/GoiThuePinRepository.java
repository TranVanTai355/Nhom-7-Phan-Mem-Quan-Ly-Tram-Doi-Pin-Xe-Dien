package com.nhom7.quanlytrampin.repository;

import com.nhom7.quanlytrampin.entity.GoiThuePin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoiThuePinRepository extends JpaRepository<GoiThuePin, Long> {
}