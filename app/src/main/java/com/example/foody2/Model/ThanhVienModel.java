package com.example.foody2.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThanhVienModel implements Parcelable {
    String hoten;
    String hinhanh;
    String mathanhvien;

    protected ThanhVienModel(Parcel in) {
        hoten = in.readString();
        hinhanh = in.readString();
        mathanhvien = in.readString();
    }

    public static final Creator<ThanhVienModel> CREATOR = new Creator<ThanhVienModel>() {
        @Override
        public ThanhVienModel createFromParcel(Parcel in) {
            return new ThanhVienModel(in);
        }

        @Override
        public ThanhVienModel[] newArray(int size) {
            return new ThanhVienModel[size];
        }
    };

    public String getMathanhvien() {
        return mathanhvien;
    }

    public void setMathanhvien(String mathanhvien) {
        this.mathanhvien = mathanhvien;
    }

    private DatabaseReference   dataNodeThanhVien;
    public ThanhVienModel() {
        dataNodeThanhVien= FirebaseDatabase.getInstance().getReference().child("thanhviens");
    }

    public ThanhVienModel(String hoten, String hinhanh) {
        this.hoten = hoten;
        this.hinhanh = hinhanh;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
    public void ThemThongTinThanhVien(ThanhVienModel thanhVienModel,String uid){

        dataNodeThanhVien.child(uid).setValue(thanhVienModel);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hoten);
        dest.writeString(hinhanh);
        dest.writeString(mathanhvien);
    }
}