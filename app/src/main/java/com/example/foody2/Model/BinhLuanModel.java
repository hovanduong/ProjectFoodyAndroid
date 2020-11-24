package com.example.foody2.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class BinhLuanModel implements Parcelable {
    double chamdiem;
    long luotthich;
    ThanhVienModel thanhVienModel; // la mot object ( chuyển về Parcelable để truyển được nhiêu dữ liệu hơn nhẹn hơn)
    String noidung;
    String tieude;
    String mauser;
    List<String> hinhanhbinhluan;
    String mabinhluan;

    protected BinhLuanModel(Parcel in) {
        chamdiem = in.readDouble();
        luotthich = in.readLong();
        noidung = in.readString();
        tieude = in.readString();
        mauser = in.readString();
        hinhanhbinhluan = in.createStringArrayList();
        mabinhluan = in.readString();
        // khoi tạo để đọc dư liệu từ thanh viên model
        thanhVienModel=in.readParcelable(ThanhVienModel.class.getClassLoader());
    }

    public static final Creator<BinhLuanModel> CREATOR = new Creator<BinhLuanModel>() {
        @Override
        public BinhLuanModel createFromParcel(Parcel in) {
            return new BinhLuanModel(in);
        }

        @Override
        public BinhLuanModel[] newArray(int size) {
            return new BinhLuanModel[size];
        }
    };

    public String getMabinhluan() {
        return mabinhluan;
    }

    public void setMabinhluan(String mabinhluan) {
        this.mabinhluan = mabinhluan;
    }

    public List<String> getHinhanhbinhluan() {
        return hinhanhbinhluan;
    }

    public void setHinhanhbinhluan(List<String> hinhanhlist) {
        this.hinhanhbinhluan = hinhanhlist;
    }

    public String getMauser() {
        return mauser;
    }

    public void setMauser(String mauser) {
        this.mauser = mauser;
    }

    public BinhLuanModel() {
    }

    public double getChamdiem() {
        return chamdiem;
    }

    public void setChamdiem(double chamdiem) {
        this.chamdiem = chamdiem;
    }

    public long getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(long luotthich) {
        this.luotthich = luotthich;
    }

    public ThanhVienModel getThanhVienModel() {
        return thanhVienModel;
    }

    public void setThanhVienModel(ThanhVienModel thanhVienModel) {
        this.thanhVienModel = thanhVienModel;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(chamdiem);
        dest.writeLong(luotthich);
        dest.writeString(noidung);
        dest.writeString(tieude);
        dest.writeString(mauser);
        dest.writeStringList(hinhanhbinhluan);
        dest.writeString(mabinhluan);
        dest.writeParcelable(thanhVienModel,flags);
    }
}
