package com.example.foody2.Controller;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Adapter.AdapterDanhSachWifi;
import com.example.foody2.Adapter.AdapterLichSuMuaHang;
import com.example.foody2.Controller.interfaces.LichSuOderInterface;
import com.example.foody2.Model.LichSuOder;
import com.example.foody2.R;

import java.util.ArrayList;
import java.util.List;


public class LichSuOderController {
    LichSuOder lichSuOder;
    Context context;
    List<LichSuOder> lichSuOders;
    public void CapNhatLichSuOderController(Context context){
        // khởi tạo đối tượng cần dử dụng
        lichSuOder=new LichSuOder();
        this.context=context;
    }
    public void ThemLichsuOder(Context context, List<LichSuOder> lichSuOders, String getUid,LichSuOder lichSuOder){
        lichSuOder.ThemOderQuanAn(context,lichSuOders,getUid,lichSuOder);

    }
//    public void HienThiKey(String Uid, RecyclerView recyclerView){
//        lichSuOders=new ArrayList<>();
//        LichSuOderInterface lichSuOderInterface=new LichSuOderInterface() {
//            @Override
//            public void HienThiDanhSachKey(LichSuOder lichSuOder) {
////                lichSuOders.add(lichSuOder);
////                AdapterLichSuMuaHang adapterLichSuMuaHang=new AdapterLichSuMuaHang(context,lichSuOders, R.layout.custom_lichsumuahang);
////                recyclerView.setAdapter(adapterLichSuMuaHang);
////                adapterLichSuMuaHang.notifyDataSetChanged();
//            }
//        };
//        lichSuOder.getKey(Uid,lichSuOderInterface);
//    }
}
