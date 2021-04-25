package com.example.foody2.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foody2.Adapter.AdapterGiaoHang;
import com.example.foody2.Model.GiaoHangModel;
import com.example.foody2.Model.QuanAnModel;
import com.example.foody2.R;

import java.util.ArrayList;
import java.util.List;

public class Giaohang extends AppCompatActivity {
    RecyclerView recyclerView;
    List<GiaoHangModel> listGH;
    AdapterGiaoHang adapterGiaoHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_layout_recyclerview_giaohang);
        initUi();
        getGiaohang();
    }

    private void getGiaohang() {

    }

    private void initUi() {

        listGH = new ArrayList<>();

        adapterGiaoHang = new AdapterGiaoHang(listGH,Giaohang.this);
        recyclerView = findViewById(R.id.recyclerViewGH);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(adapterGiaoHang);
    }
}