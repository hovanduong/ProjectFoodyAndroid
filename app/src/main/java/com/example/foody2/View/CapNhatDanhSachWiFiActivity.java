package com.example.foody2.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foody2.Controller.CapNhatWiFiController;
import com.example.foody2.R;

public class CapNhatDanhSachWiFiActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerDachSachWifi;
    Button btnCapNhatWifi;
    CapNhatWiFiController capNhatWiFiController;
    String maquanan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_danh_sach_wi_fi);
        btnCapNhatWifi=findViewById(R.id.btnCapNhatWifi);
        recyclerDachSachWifi=findViewById(R.id.recyclerDanhSachWifi);
         maquanan=getIntent().getStringExtra("maquanan");
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true);// cột true chính là đảo ngược dữ liệu và ngược lại
        recyclerDachSachWifi.setLayoutManager(layoutManager);

        capNhatWiFiController=new CapNhatWiFiController(this);
        capNhatWiFiController.HienThiDanhSachWifi(maquanan,recyclerDachSachWifi);
        btnCapNhatWifi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent iPopup =new Intent(CapNhatDanhSachWiFiActivity.this,PopupCapNhatWifiAcitivity.class);
        iPopup.putExtra("maquanan",maquanan);
        startActivity(iPopup);
    }
}
