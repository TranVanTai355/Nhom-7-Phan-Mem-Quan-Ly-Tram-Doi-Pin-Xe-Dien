package com.nhom7.quanlytrampin.repository;

import com.nhom7.quanlytrampin.entity.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long> {

}

