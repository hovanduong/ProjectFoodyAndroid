package com.example.foody2.Controller;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Adapter.AdapterDanhSachWifi;
import com.example.foody2.Controller.interfaces.ChiTietQuanAnInterface;
import com.example.foody2.Model.WiFiQuanAnModel;
import com.example.foody2.R;

import java.util.ArrayList;
import java.util.List;

public class CapNhatWiFiController {
    WiFiQuanAnModel wiFiQuanAnModel;
    Context context;
    List<WiFiQuanAnModel> wiFiQuanAnModelList;
    public CapNhatWiFiController(Context context){
        // khởi tạo đối tượng cần dử dụng
         wiFiQuanAnModel=new WiFiQuanAnModel();
         this.context=context;
    }
    public  void HienThiDanhSachWifi(String maquanan, final RecyclerView recyclerView){
        wiFiQuanAnModelList =new ArrayList<>();
// hàm này được kích hoạt khi wifiquananModel trả dữ liệu về
        ChiTietQuanAnInterface chiTietQuanAnInterface=new ChiTietQuanAnInterface() {
            @Override
            public void HienThiDanhSachWifi(WiFiQuanAnModel wiFiQuanAnModel) {
                wiFiQuanAnModelList.add(wiFiQuanAnModel);
                AdapterDanhSachWifi adapterDanhSachWifi=new AdapterDanhSachWifi(context, R.layout.layout_wifi_chitietquanan,wiFiQuanAnModelList);
                recyclerView.setAdapter(adapterDanhSachWifi);
                adapterDanhSachWifi.notifyDataSetChanged();
            }
        };
        wiFiQuanAnModel.LayDanhSachWiFi(maquanan,chiTietQuanAnInterface);
    }
    public void ThemWifi(Context context,WiFiQuanAnModel wiFiQuanAnModel,String maquanan){
        wiFiQuanAnModel.ThemWifiQuanAn(context,wiFiQuanAnModel,maquanan);
    }
}
