package com.example.gkiandroid;

public class NhanVien {
    private String mannv;
    private String hoten;
    private String gioitinh;

    public String getMannv() {
        return mannv;
    }

    public void setMannv(String mannv) {
        this.mannv = mannv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public NhanVien(String mannv, String hoten, String gioitinh) {
        this.mannv = mannv;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
    }

    public NhanVien() {

    }

    @Override
    public String toString() {
        return this.mannv + "-" +this.hoten + "-" +this.gioitinh;
    }
}
