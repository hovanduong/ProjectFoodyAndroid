package com.example.foody2.Model;



import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foody2.Controller.interfaces.ChiTietQuanAnInterface;
import com.example.foody2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WiFiQuanAnModel {

    public WiFiQuanAnModel() {
    }

    String ten,matkhau,ngaydang;

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
    }

    public String getTen() {
        return ten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public String getNgaydang() {
        return ngaydang;
    }
    private DatabaseReference nodeWifiQuanAn;
    public void LayDanhSachWiFi(String maquanan, final ChiTietQuanAnInterface chiTietQuanAnInterface){

      nodeWifiQuanAn= FirebaseDatabase.getInstance().getReference().child("wifiquanans").child(maquanan);
      //  nodeWifiQuanAn= FirebaseDatabase.getInstance().getReference().child("wifiquanans");

        // lắng nghe một lần
        nodeWifiQuanAn.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //dataSnapshot đại diện cho các mã wifi
                for(DataSnapshot valueWifi : dataSnapshot.getChildren()){

                    WiFiQuanAnModel wiFiQuanAnModel=valueWifi.getValue(WiFiQuanAnModel.class);
                    chiTietQuanAnInterface.HienThiDanhSachWifi(wiFiQuanAnModel);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void ThemWifiQuanAn(final Context context, WiFiQuanAnModel wiFiQuanAnModel,String maquanan){
        //Ghi dữ liệu vào FireBase
        DatabaseReference dataNodeWifiQuanAn= FirebaseDatabase.getInstance().getReference().child("wifiquanans").child(maquanan);
        dataNodeWifiQuanAn.push().setValue(wiFiQuanAnModel, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                Toast.makeText(context, context.getResources().getString(R.string.themthanhcong),Toast.LENGTH_LONG).show();
            }
        });

    }
}