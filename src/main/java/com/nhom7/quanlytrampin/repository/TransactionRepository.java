package com.nhom7.quanlytrampin.repository;

import com.nhom7.quanlytrampin.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Thêm các hàm tìm kiếm nếu cần, ví dụ:
    List<Transaction> findByDriverId(Long driverId);
    
    List<Transaction> findByStatus(String status);
}