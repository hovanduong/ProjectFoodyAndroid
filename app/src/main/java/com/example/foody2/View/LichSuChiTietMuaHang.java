package com.example.foody2.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.foody2.R;

public class LichSuChiTietMuaHang extends AppCompatActivity {
    String ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_chi_tiet_mua_hang);
        ma=getIntent().getParcelableExtra("machitiet");
        Log.d("kiemtra1111",ma);
    }
}