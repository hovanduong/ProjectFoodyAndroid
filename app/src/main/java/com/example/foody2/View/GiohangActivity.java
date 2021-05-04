package com.example.foody2.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.foody2.R;

public class GiohangActivity extends AppCompatActivity {
    TextView txtTenmonanngiohang, txtgiamonangiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);

        txtTenmonanngiohang = findViewById(R.id.txtTenmonanngiohang);
        txtgiamonangiohang = findViewById(R.id.txtgiamonangiohang);

        Intent intent = getIntent();
        String tenmonan = intent.getStringExtra("tenmonan");
        String giamonan = intent.getStringExtra("giamonan");

        txtgiamonangiohang.setText(giamonan);
        txtTenmonanngiohang.setText(tenmonan);


    }
}