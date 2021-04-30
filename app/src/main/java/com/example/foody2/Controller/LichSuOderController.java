package com.example.foody2.Controller;

import android.content.Context;

import com.example.foody2.Model.LichSuOder;


public class LichSuOderController {
    LichSuOder lichSuOder;
    Context context;
    public void CapNhatLichSuOderController(Context context){
        // khởi tạo đối tượng cần dử dụng
        lichSuOder=new LichSuOder();
        this.context=context;
    }
    public void ThemLichsuOder(Context context, LichSuOder lichSuOder, String getUid){
        lichSuOder.ThemOderQuanAn(context,lichSuOder,getUid);
    }
}
