package com.example.foody2.Controller;

import android.content.Context;

import com.example.foody2.Model.LichSuOder;

import java.util.List;


public class LichSuOderController {
    LichSuOder lichSuOder;
    Context context;
    public void CapNhatLichSuOderController(Context context){
        // khởi tạo đối tượng cần dử dụng
        lichSuOder=new LichSuOder();
        this.context=context;
    }
    public void ThemLichsuOder(Context context, List<LichSuOder> lichSuOders, String getUid,LichSuOder lichSuOder){
        lichSuOder.ThemOderQuanAn(context,lichSuOders,getUid,lichSuOder);

    }
}
