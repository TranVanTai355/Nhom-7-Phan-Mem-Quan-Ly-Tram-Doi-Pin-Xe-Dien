package com.nhom7.quanlytrampin.repository;

import com.nhom7.quanlytrampin.entity.TramPin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TramPinRepository extends JpaRepository<TramPin, Long> {
    List<TramPin> findByTrangThaiHoatDong(boolean status);

    Optional<TramPin> findByTenTramPin(String tenTramPin);
}
