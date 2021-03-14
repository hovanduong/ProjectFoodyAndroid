package com.example.foody2.Model;

public class TienIchModel {
    String hinhtienich,tentienich;
    String maTienIch;

    public TienIchModel() {
    }

    public TienIchModel(String hinhtienich, String tentienich, String maTienIch) {
        this.hinhtienich = hinhtienich;
        this.tentienich = tentienich;
        this.maTienIch = maTienIch;
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

    public String getMaTienIch() {
        return maTienIch;
    }

    public void setMaTienIch(String maTienIch) {
        this.maTienIch = maTienIch;
    }
}
