package com.nhom7.quanlytrampin.service;

import com.nhom7.quanlytrampin.entity.Battery;
import com.nhom7.quanlytrampin.repository.BatteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private BatteryRepository batteryRepository;

    public Map<String, Long> trackBatteries() {
        List<Battery> batteries = batteryRepository.findAll();
        return batteries.stream()
                .collect(Collectors.groupingBy(Battery::getStatus, Collectors.counting()));
    }

    public Map<String, List<Battery>> categorizeBatteries() {
        List<Battery> batteries = batteryRepository.findAll();
        return batteries.stream()
                .collect(Collectors.groupingBy(b -> b.getModel() + "_" + b.getCapacity() + "_" + b.getStatus()));
    }
}
