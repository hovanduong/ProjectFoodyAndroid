package com.example.foody2.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.foody2.Adapter.AdapterBinhLuan;
import com.example.foody2.Adapter.AdapterDanhSachMenu;
import com.example.foody2.Model.QuanAnModel;
import com.example.foody2.R;

public class DatMonAnActivity extends AppCompatActivity {
    TextView txtTieuDeToolBar;
    QuanAnModel quanAnModel;
    Toolbar toolbar;
    RecyclerView recyclerViewMenuQuanAn;
    AdapterDanhSachMenu adapterDanhSachMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_mon_an);

        quanAnModel= getIntent().getParcelableExtra("quanan1");


        // xử lý tool bar
        txtTieuDeToolBar=findViewById(R.id.txtTieuDeToolBar);
        txtTieuDeToolBar.setText(quanAnModel.getTenquanan());

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);

        // hiển thị nút bên trái toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        // load danh sach menu
        recyclerViewMenuQuanAn=findViewById(R.id.recyclerViewMenuQuanAn);

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerViewMenuQuanAn.setLayoutManager(layoutManager);
        //Log.d("Kiem tra",quanAnModel.getMenuQuanAnList() + "");
        adapterDanhSachMenu=new AdapterDanhSachMenu(this,R.layout.custom_layout_recyclerview_datmonan ,quanAnModel.getMenuQuanAnList());
        recyclerViewMenuQuanAn.setAdapter(adapterDanhSachMenu);
        adapterDanhSachMenu.notifyDataSetChanged();


    }
    // ham này thực thi khi nhấn vào nút quay trở lại phía trên tool bar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}