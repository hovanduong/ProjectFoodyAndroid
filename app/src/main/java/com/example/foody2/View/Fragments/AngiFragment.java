package com.example.foody2.View.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Adapter.AdapterGioHang;
import com.example.foody2.Adapter.AdapterMonAn;
import com.example.foody2.Controller.GioHangContronller;
import com.example.foody2.Model.DatMon;
import com.example.foody2.R;

import java.util.ArrayList;
import java.util.List;

public class AngiFragment extends Fragment {
    //GioHangContronller gioHangContronller;
    Context context;
    RecyclerView recyclerViewGioHang;
    AdapterGioHang adapterGioHang;
  public static   List<DatMon> datMonList=new ArrayList<>();
   // DatMon datMon;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment_angi,container,false);
            recyclerViewGioHang=view.findViewById(R.id.recyclerViewGioHang);
//        gioHangContronller=new GioHangContronller(getContext());
//        gioHangContronller.getDanhSachDatMon();
        Log.d("kiemtra",datMonList + "");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewGioHang.setLayoutManager(layoutManager);
        // recyclerViewGioHang.setLayoutManager(new LinearLayoutManager(context));
        adapterGioHang = new AdapterGioHang(context,datMonList, R.layout.custom_layout_monan);
        recyclerViewGioHang.setAdapter(adapterGioHang);
        adapterGioHang.notifyDataSetChanged();
        return view;
    }
    public AngiFragment(){

    }
    public void getDanhSachDatMon(){

        for( DatMon datMon : AdapterMonAn.datMonList){
            datMonList.add(datMon);
            Log.d("kiemtra",datMon.getTenMonAn());
        }



    }


}
