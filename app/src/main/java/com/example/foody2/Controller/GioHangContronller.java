package com.example.foody2.Controller;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Adapter.AdapterGioHang;
import com.example.foody2.Adapter.AdapterMonAn;
import com.example.foody2.Adapter.ApdaterRecyclerOdau;
import com.example.foody2.Model.DatMon;
import com.example.foody2.R;

import java.util.ArrayList;
import java.util.List;

public class GioHangContronller {
    Context context;
    AdapterGioHang adapterGioHang;
    DatMon datMon;
    RecyclerView recyclerView;
    public GioHangContronller(Context context){
        this.context=context;
        datMon=new DatMon();
    }
    public void getDanhSachDatMon(){


        List<DatMon> datMonList=new ArrayList<>();
        for( DatMon datMon : AdapterMonAn.datMonList){
            datMonList.add(datMon);
           Log.d("kiemtra",datMon.getTenMonAn());
       }
       // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
//        recyclerViewGioHang.setLayoutManager(layoutManager);
//        recyclerViewGioHang.setLayoutManager(new LinearLayoutManager(context));
//        adapterGioHang = new AdapterGioHang(context,datMonList, R.layout.custom_layout_monan);
//        recyclerViewGioHang.setAdapter(adapterGioHang);
//        adapterGioHang.notifyDataSetChanged();


    }
}
