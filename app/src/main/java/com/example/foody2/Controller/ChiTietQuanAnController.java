package com.example.foody2.Controller;


import android.widget.TextView;

import com.example.foody2.Controller.interfaces.ChiTietQuanAnInterface;
import com.example.foody2.Model.WiFiQuanAnModel;

import java.util.ArrayList;
import java.util.List;

public class ChiTietQuanAnController {
    WiFiQuanAnModel wiFiQuanAnModel;
    List<WiFiQuanAnModel> wiFiQuanAnModelList;
    public ChiTietQuanAnController(){
        wiFiQuanAnModel=new WiFiQuanAnModel();
        wiFiQuanAnModelList =new ArrayList<>();

    }
    public void HienThiDanhSachWifWi(String maquanan, final TextView txtTenWifi, final TextView txtMatKhauWifi, final TextView txtNgayDangWifi){
        // hàm này được kích hoạt khi wifiquananModel trả dữ liệu về
        ChiTietQuanAnInterface chiTietQuanAnInterface=new ChiTietQuanAnInterface() {
            @Override
            public void HienThiDanhSachWifi(WiFiQuanAnModel wiFiQuanAnModel) {

                wiFiQuanAnModelList.add(wiFiQuanAnModel);

               txtTenWifi.setText(wiFiQuanAnModel.getTen());
                txtMatKhauWifi.setText(wiFiQuanAnModel.getMatkhau());
                txtNgayDangWifi.setText(wiFiQuanAnModel.getNgaydang());
            }
        };
        wiFiQuanAnModel.LayDanhSachWiFi(maquanan,chiTietQuanAnInterface);
    }
}
