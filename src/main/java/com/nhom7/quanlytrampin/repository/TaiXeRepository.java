package com.nhom7.quanlytrampin.repository;

import com.nhom7.quanlytrampin.entity.TaiXe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TaiXeRepository extends JpaRepository<TaiXe, Long> {

    Optional<TaiXe> findByEmail(String email);
}