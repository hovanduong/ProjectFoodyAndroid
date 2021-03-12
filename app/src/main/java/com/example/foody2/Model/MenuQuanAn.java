package com.example.foody2.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuQuanAn implements Parcelable {
    String hinhanh;
    String tenmon;
    String solandat;
    String giatien;
    String mamenu;

    public MenuQuanAn() {
    }

    public MenuQuanAn(String hinhanh, String tenmon, String solandat, String giatien, String mamenu) {
        this.hinhanh = hinhanh;
        this.tenmon = tenmon;
        this.solandat = solandat;
        this.giatien = giatien;
        this.mamenu = mamenu;
    }

    protected MenuQuanAn(Parcel in) {
        hinhanh = in.readString();
        tenmon = in.readString();
        solandat = in.readString();
        giatien = in.readString();
        mamenu = in.readString();
    }

    public static final Creator<MenuQuanAn> CREATOR = new Creator<MenuQuanAn>() {
        @Override
        public MenuQuanAn createFromParcel(Parcel in) {
            return new MenuQuanAn(in);
        }

        @Override
        public MenuQuanAn[] newArray(int size) {
            return new MenuQuanAn[size];
        }
    };

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public String getSolandat() {
        return solandat;
    }

    public void setSolandat(String solandat) {
        this.solandat = solandat;
    }

    public String getGiatien() {
        return giatien;
    }

    public void setGiatien(String giatien) {
        this.giatien = giatien;
    }

    public String getMamenu() {
        return mamenu;
    }

    public void setMamenu(String mamenu) {
        this.mamenu = mamenu;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hinhanh);
        dest.writeString(tenmon);
        dest.writeString(solandat);
        dest.writeString(giatien);
        dest.writeString(mamenu);
    }
}
