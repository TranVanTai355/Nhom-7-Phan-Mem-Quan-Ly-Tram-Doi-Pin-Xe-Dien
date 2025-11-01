package com.nhom7.quanlytrampin.dto;

import java.math.BigDecimal;

public class PaymentRequestDto {

    private Long maTaiXe;
    private BigDecimal soTien;
    private String noiDung;
    private String phuongThuc;
    private Long maGoi;

    public Long getMaTaiXe() { return maTaiXe; }
    public void setMaTaiXe(Long maTaiXe) { this.maTaiXe = maTaiXe; }
    public BigDecimal getSoTien() { return soTien; }
    public void setSoTien(BigDecimal soTien) { this.soTien = soTien; }
    public String getNoiDung() { return noiDung; }
    public void setNoiDung(String noiDung) { this.noiDung = noiDung; }
    public String getPhuongThuc() { return phuongThuc; }
    public void setPhuongThuc(String phuongThuc) { this.phuongThuc = phuongThuc; }
    public Long getMaGoi() { return maGoi; }
    public void setMaGoi(Long maGoi) { this.maGoi = maGoi; }
}