package com.nhom7.quanlytrampin.repository;

import com.nhom7.quanlytrampin.entity.Pin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PinRepository {
    List<Pin> findByfindByLoaiPinVaTrangThaiPin(String loaiPin, String trangThai);

    Optional<Pin> findFirstByTramPin_MaTramVaLoaiPinVaTrangThaiPin(Long maTram, String loaiPin, String trangThai);
}
