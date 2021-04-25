package com.example.foody2.Model;

import java.util.List;

public class GiaoHangModel {
    Boolean giaohang;
    String tenquanan;
    String diachi;

    public GiaoHangModel(Boolean giaohang, String tenquanan, String diachi) {
        this.giaohang = giaohang;
        this.tenquanan = tenquanan;
        this.diachi = diachi;
    }

    public Boolean getGiaohang() {
        return giaohang;
    }

    public void setGiaohang(Boolean giaohang) {
        this.giaohang = giaohang;
    }

    public String getTenquanan() {
        return tenquanan;
    }

    public void setTenquanan(String tenquanan) {
        this.tenquanan = tenquanan;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
//    List<String> hinhanhquanan;


}
