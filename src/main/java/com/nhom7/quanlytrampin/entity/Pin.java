package com.nhom7.quanlytrampin.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Pin")
public class Pin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaPin")
    private Long maPin;

    @Column(name = "LoaiPin")
    private String loaiPin; 

    @Column(name = "DungLuong")
    private int dungLuong; 

    @Column(name = "TrangThai", nullable = false)
    private String trangThai; 

    @Column(name = "StateOfHealth")
    private int soh;

    @Column(name = "Model")
    private String model;

    @Column(name = "NgayCapNhatTrangThai")
    private LocalDateTime ngayCapNhatTrangThai; 

    @ManyToOne
    @JoinColumn(name = "MaTramPin", nullable = false)
    private TramPin tramPin; 

    
    public Long getMaPin() { return maPin; }
    public void setMaPin(Long maPin) { this.maPin = maPin; }

    public String getLoaiPin() { return loaiPin; }
    public void setLoaiPin(String loaiPin) { this.loaiPin = loaiPin; }

    public int getDungLuong() { return dungLuong; }
    public void setDungLuong(int dungLuong) { this.dungLuong = dungLuong; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public int getSoh() { return soh; }
    public void setSoh(int soh) { this.soh = soh; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public LocalDateTime getNgayCapNhatTrangThai() { return ngayCapNhatTrangThai; }
    public void setNgayCapNhatTrangThai(LocalDateTime ngayCapNhatTrangThai) {
        this.ngayCapNhatTrangThai = ngayCapNhatTrangThai;
    }

    public TramPin getTramPin() { return tramPin; }
    public void setTramPin(TramPin tramPin) { this.tramPin = tramPin; }
}
