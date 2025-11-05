package com.nhom7.quanlytrampin.repository;

import com.nhom7.quanlytrampin.entity.Pin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PinRepository extends JpaRepository<Pin, Long> {
  
    List<Pin> findByLoaiPinAndTrangThai(String loaiPin, String trangThai);

    Optional<Pin> findFirstByTramPin_MaTramPinAndLoaiPinAndTrangThai(
        Long maTramPin, 
        String loaiPin, 
        String trangThai
    );
    
    List<Pin> findByTramPin_MaTramPin(Long maTramPin);
    
    long countByTramPin_MaTramPinAndTrangThai(Long maTramPin, String trangThai);
    
    List<Pin> findByTramPin_MaTramPinAndLoaiPin(Long maTramPin, String loaiPin);
    
    long countByTramPin_MaTramPinAndLoaiPinAndTrangThai(
        Long maTramPin, 
        String loaiPin, 
        String trangThai
    );
}