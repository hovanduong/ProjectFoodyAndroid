package com.example.foody2.Model;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foody2.Controller.interfaces.OdauInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuanAnModel implements Parcelable {
    boolean giaohang;
    String giodongcua;
    String giomocua;
    String tenquanan;
    String videogioithieu;
    String maquanan;
    long luotthich;
    List<String> tienich;
    List<String> hinhanhquanan;
    List<BinhLuanModel> binhluanModeList;
    List<Bitmap> bitmapList;
    long giatoida;

    public long getGiatoida() {
        return giatoida;
    }

    public void setGiatoida(long giatoida) {
        this.giatoida = giatoida;
    }

    public long getGiatoithieu() {
        return giatoithieu;
    }

    public void setGiatoithieu(long giatoithieu) {
        this.giatoithieu = giatoithieu;
    }

    long giatoithieu;
// bước đọc
    protected QuanAnModel(Parcel in) {
        giaohang = in.readByte() != 0;
        giodongcua = in.readString();
        giomocua = in.readString();
        tenquanan = in.readString();
        videogioithieu = in.readString();
        tienich = in.createStringArrayList();
        hinhanhquanan = in.createStringArrayList();
      //  bitmapList = in.createTypedArrayList(Bitmap.CREATOR);
        maquanan = in.readString();
        luotthich = in.readLong();
        giatoida=in.readLong();
        giatoithieu=in.readLong();
        // dọc dữ liệu cho đối tượng
        chiNhanhQuanAnModelList=new ArrayList<ChiNhanhQuanAnModel>(); // neu null thi khời tạo arraylisst
        in.readTypedList(chiNhanhQuanAnModelList,ChiNhanhQuanAnModel.CREATOR);
        binhluanModeList=new ArrayList<BinhLuanModel>();
        in.readTypedList(binhluanModeList,BinhLuanModel.CREATOR);
    }

    public static final Creator<QuanAnModel> CREATOR = new Creator<QuanAnModel>() {
        @Override
        public QuanAnModel createFromParcel(Parcel in) {
            return new QuanAnModel(in);
        }

        @Override
        public QuanAnModel[] newArray(int size) {
            return new QuanAnModel[size];
        }
    };

    public List<Bitmap> getBitmapList() {
        return bitmapList;
    }

    public void setBitmapList(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
    }

    public List<ChiNhanhQuanAnModel> getChiNhanhQuanAnModelList() {
        return chiNhanhQuanAnModelList;
    }

    public void setChiNhanhQuanAnModelList(List<ChiNhanhQuanAnModel> chiNhanhQuanAnModelList) {
        this.chiNhanhQuanAnModelList = chiNhanhQuanAnModelList;
    }

    List<ChiNhanhQuanAnModel> chiNhanhQuanAnModelList;

    public List<BinhLuanModel> getBinhluanModeList() {
        return binhluanModeList;
    }

    public void setBinhluanModeList(List<BinhLuanModel> binhluanModeList) {
        this.binhluanModeList = binhluanModeList;
    }


    private DatabaseReference nodeRoot;


    public List<String> getHinhanhquanan() {
        return hinhanhquanan;
    }

    public void setHinhanhquanan(List<String> hinhanhquanan) {
        this.hinhanhquanan = hinhanhquanan;
    }

    public long getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(long luotthich) {
        this.luotthich = luotthich;
    }

    public QuanAnModel(boolean giaohang, String giodongcua, String giomocua, String tenquanan, String videogioithieu, List<String> tienich, String maquanan) {
        this.giaohang = giaohang;
        this.giodongcua = giodongcua;
        this.giomocua = giomocua;
        this.tenquanan = tenquanan;
        this.videogioithieu = videogioithieu;
        this.tienich = tienich;
        this.maquanan = maquanan;
    }

    public QuanAnModel() {
        nodeRoot = FirebaseDatabase.getInstance().getReference();
    }

    public boolean isGiaohang() {
        return giaohang;
    }

    public void setGiaohang(boolean giaohang) {
        this.giaohang = giaohang;
    }

    public String getGiodongcua() {
        return giodongcua;
    }

    public void setGiodongcua(String giodongcua) {
        this.giodongcua = giodongcua;
    }

    public String getGiomocua() {
        return giomocua;
    }

    public void setGiomocua(String giomocua) {
        this.giomocua = giomocua;
    }

    public String getTenquanan() {
        return tenquanan;
    }

    public void setTenquanan(String tenquanan) {
        this.tenquanan = tenquanan;
    }

    public String getVideogioithieu() {
        return videogioithieu;
    }

    public void setVideogioithieu(String videogioithieu) {
        this.videogioithieu = videogioithieu;
    }

    public List<String> getTienich() {
        return tienich;
    }

    public void setTienich(List<String> tienich) {
        this.tienich = tienich;
    }

    public String getMaquanan() {
        return maquanan;
    }

    public void setMaquanan(String maquanan) {
        this.maquanan = maquanan;
    }
    private  DataSnapshot dataRoot;

    public void getDanhSachQuanAn(final OdauInterface odauInterface, final Location vitrihientai, final int itemtieptheo, final int itemdaco) {

        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataRoot=dataSnapshot;
                LayDanhSachQuanAn(dataSnapshot,odauInterface,vitrihientai,itemtieptheo,itemdaco );
            }

            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        if(dataRoot != null){
            LayDanhSachQuanAn(dataRoot,odauInterface,vitrihientai,itemtieptheo,itemdaco );

        }else{
            nodeRoot.addListenerForSingleValueEvent(valueEventListener);
        }

    }

    private void LayDanhSachQuanAn(DataSnapshot dataSnapshot, OdauInterface odauInterface, Location vitrihientai, int itemtieptheo, int itemdaco) {
        DataSnapshot dataSnapshotQuanAn = dataSnapshot.child("quanans");
        //lay danh sach quan ăn
        int i = 0;
        for (DataSnapshot valueQuanAn : dataSnapshotQuanAn.getChildren()) {
            if(i==itemtieptheo){
                break;
            }
            if(i<itemdaco){
                i++;
                continue;
            }
            i++;
            QuanAnModel quanAnModel = valueQuanAn.getValue(QuanAnModel.class);
            quanAnModel.setMaquanan(valueQuanAn.getKey());

           // Log.d("kiemtra", valueQuanAn.getKey() + "");
            //lay danh sách hình ảnh của quán ăn theo mã
            DataSnapshot dataSnapshotHinhQuanAn = dataSnapshot.child("hinhanhquanans").child(valueQuanAn.getKey());

            List<String> hinhanhlist = new ArrayList<>();
            for (DataSnapshot valueHinhQuanAn : dataSnapshotHinhQuanAn.getChildren()) {
                hinhanhlist.add(valueHinhQuanAn.getValue(String.class));
            }
            quanAnModel.setHinhanhquanan(hinhanhlist);
            // Lấy danh sách bình luận của quán ăn
            DataSnapshot dataSnapshotBinhLuanQuanAn = dataSnapshot.child("binhluans").child(quanAnModel.getMaquanan());

            List<BinhLuanModel> binhLuanModels = new ArrayList<>();
            for (DataSnapshot valueBinhLuan : dataSnapshotBinhLuanQuanAn.getChildren()) {
                BinhLuanModel binhLuanModel = valueBinhLuan.getValue(BinhLuanModel.class);
                binhLuanModel.setMabinhluan(valueBinhLuan.getKey());
                // lay user của thành viên
                ThanhVienModel thanhVienModel = dataSnapshot.child("thanhviens").
                        child(binhLuanModel.getMauser()).getValue(ThanhVienModel.class);
                binhLuanModel.setThanhVienModel(thanhVienModel);

                List<String> hinanhbinhluanlist = new ArrayList<>();
                DataSnapshot dataSnapshotHinhAnhBinhLuan = dataSnapshot.child("hinhanhbinhluans").child(binhLuanModel.getMabinhluan());
                for (DataSnapshot valueHinhAnhBinhLuan : dataSnapshotHinhAnhBinhLuan.getChildren()) {
                    hinanhbinhluanlist.add(valueHinhAnhBinhLuan.getValue(String.class));

                }
                binhLuanModel.setHinhanhbinhluan(hinanhbinhluanlist);
                binhLuanModels.add(binhLuanModel);
            }
            quanAnModel.setBinhluanModeList(binhLuanModels);
            // lay chi nhánh bình luận
            List<ChiNhanhQuanAnModel> chiNhanhQuanAnModels = new ArrayList<>();
            DataSnapshot dataSnapshotChiNhanhQuanAn = dataSnapshot.child("chinhanhquanans").child(quanAnModel.getMaquanan());
            for (DataSnapshot valueChiNhanhQuanAn : dataSnapshotChiNhanhQuanAn.getChildren()) {
                ChiNhanhQuanAnModel chiNhanhQuanAnModel = valueChiNhanhQuanAn.getValue(ChiNhanhQuanAnModel.class);
                Location vitriquanan = new Location("");
                vitriquanan.setLatitude(chiNhanhQuanAnModel.getLatitude());
                vitriquanan.setLongitude(chiNhanhQuanAnModel.getLongitude());
                double khoangcach = vitrihientai.distanceTo(vitriquanan) / 1000;
              //  Log.d("kiemtra", khoangcach + "-" + chiNhanhQuanAnModel.getDiachi());
                chiNhanhQuanAnModel.setKhoangcach(khoangcach);
                chiNhanhQuanAnModels.add(chiNhanhQuanAnModel);
            }
            quanAnModel.setChiNhanhQuanAnModelList(chiNhanhQuanAnModels);
            odauInterface.getDanhSachQuanAnModel(quanAnModel);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    // lưu trữ dữ liệu ( quan tron nhat)
    // bước ghi ( ghi trước rồi đến bước đọc )
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (giaohang ? 1 : 0));
        dest.writeString(giodongcua);
        dest.writeString(giomocua);
        dest.writeString(tenquanan);
        dest.writeString(videogioithieu);
        dest.writeStringList(tienich);
        dest.writeStringList(hinhanhquanan);
        dest.writeString(maquanan);
        dest.writeLong(luotthich);
        dest.writeLong(giatoida);
        dest.writeLong(giatoithieu);
        // su dung writeTypedList đẻ ghi dữ liệu cho một đối tượng
        dest.writeTypedList(chiNhanhQuanAnModelList);

        dest.writeTypedList(binhluanModeList);
    }
}
