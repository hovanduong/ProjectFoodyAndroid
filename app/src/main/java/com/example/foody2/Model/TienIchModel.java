package com.example.foody2.Model;

public class TienIchModel {
    String hinhtienich,tentienich;

    public TienIchModel() {
    }

    public TienIchModel(String hinhtienich, String tentienich) {
        this.hinhtienich = hinhtienich;
        this.tentienich = tentienich;
    }

    public String getHinhtienich() {
        return hinhtienich;
    }

    public void setHinhtienich(String hinhtienich) {
        this.hinhtienich = hinhtienich;
    }

    public String getTentienich() {
        return tentienich;
    }

    public void setTentienich(String tentienich) {
        this.tentienich = tentienich;
    }

}
