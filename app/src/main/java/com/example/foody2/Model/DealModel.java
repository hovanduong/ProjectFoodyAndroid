package com.example.foody2.Model;

import java.util.List;

public class DealModel {
    private String hinh;
    private String magiam;
    private String diadiem;

     DealModel() {
    }

    public DealModel(String hinh, String magiam, String diadiem) {
        this.hinh = hinh;
        this.magiam = magiam;
        this.diadiem = diadiem;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMagiam() {
        return magiam;
    }

    public void setMagiam(String magiam) {
        this.magiam = magiam;
    }

    public String getDiadiem() {
        return diadiem;
    }

    public void setDiadiem(String diadiem) {
        this.diadiem = diadiem;
    }
}
