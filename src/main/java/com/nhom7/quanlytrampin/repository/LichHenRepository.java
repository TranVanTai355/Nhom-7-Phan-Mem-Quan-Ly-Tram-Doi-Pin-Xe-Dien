package com.nhom7.quanlytrampin.repository;

import com.nhom7.quanlytrampin.entity.LichHen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

public interface LichHenRepository extends JpaRepository<LichHen, Long> {
    List<LichHen> findByTaiXe_MaTaiXe(Long maTaiXe);

    Optional<LichHen> findByTaiXe_MaTaiXeAndThoiGianHen(Long maTaiXe, LocalDateTime thoiGianHen);
}
