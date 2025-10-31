package com.nhom7.quanlytrampin.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PhuongTien")
public class PhuongTien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaXe")
    private Long maXe;

    @Column(name = "LoaiXe")
    private String loaiXe;

    @Column(name = "LoaiPin") 
    private String loaiPin;

    @ManyToOne
    @JoinColumn(name = "MaTaiXe", nullable = false) 
    private TaiXe taiXe;
}