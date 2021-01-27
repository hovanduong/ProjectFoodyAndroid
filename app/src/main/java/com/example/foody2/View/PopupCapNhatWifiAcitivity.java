package com.example.foody2.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foody2.Controller.CapNhatWiFiController;
import com.example.foody2.Model.WiFiQuanAnModel;
import com.example.foody2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PopupCapNhatWifiAcitivity extends AppCompatActivity implements View.OnClickListener {
    EditText edTenWifi,edMatKhauWifi;
    Button btnDongYCapNhatWifi;
    CapNhatWiFiController capNhatWiFiController;
    String maquanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_cap_nhat_wifi_acitivity);
        edMatKhauWifi=findViewById(R.id.edNhapMatKhauWifi);
        edTenWifi=findViewById(R.id.edTenWifi);
        btnDongYCapNhatWifi=findViewById(R.id.btnDongYCapNhatWifi);
        maquanan=getIntent().getStringExtra("maquanan");
        capNhatWiFiController=new CapNhatWiFiController(this);

        btnDongYCapNhatWifi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

            String tenwifi=edTenWifi.getText().toString();
            String matkhauwifi=edMatKhauWifi.getText().toString();
            if(tenwifi.trim().length() > 0 && matkhauwifi.trim().length()>0){
                Calendar calendar=Calendar.getInstance();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
                String ngaydang=simpleDateFormat.format(calendar.getTime());

                WiFiQuanAnModel wiFiQuanAnModel=new WiFiQuanAnModel();
                wiFiQuanAnModel.setTen(tenwifi);
                wiFiQuanAnModel.setMatkhau(matkhauwifi);
                wiFiQuanAnModel.setNgaydang(ngaydang);
        capNhatWiFiController.ThemWifi(this,wiFiQuanAnModel,maquanan);
        }else{
            Toast.makeText(this,getString(R.string.loithemwifi),Toast.LENGTH_LONG).show();
        }
    }

}
