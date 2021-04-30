package com.example.foody2.Model;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foody2.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class LichSuOder {
    private String tensp;
    private long soluong;
    private String giasanpham;


    public LichSuOder() {

    }

    public LichSuOder(String tensp, long soluong, String giasanpham) {
        this.tensp = tensp;
        this.soluong = soluong;
        this.giasanpham = giasanpham;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public long getSoluong() {
        return soluong;
    }

    public void setSoluong(long soluong) {
        this.soluong = soluong;
    }

    public String getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(String giasanpham) {
        this.giasanpham = giasanpham;
    }
    public void ThemOderQuanAn(final Context context, List<LichSuOder> lichSuOders, String getUid,LichSuOder lichSuOder){
        //Ghi dữ liệu vào FireBase
        DatabaseReference dataNodeWifiQuanAn= FirebaseDatabase.getInstance().getReference().child("lichsuoder").child(getUid).push();
        dataNodeWifiQuanAn.push().setValue(lichSuOders, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
//               Toast.makeText(context, "Thanh toán thành công!!",Toast.LENGTH_LONG).show();
            }
        });

    }


}
