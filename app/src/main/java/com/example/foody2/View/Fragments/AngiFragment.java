
package com.example.foody2.View.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Adapter.AdapterGioHang;
import com.example.foody2.Adapter.AdapterMonAn;
import com.example.foody2.Model.DatMon;
import com.example.foody2.R;

import java.util.ArrayList;
import java.util.List;

public class AngiFragment extends Fragment {
    //GioHangContronller gioHangContronller;
    Context context;
    private RecyclerView recyclerViewGioHang;
    AdapterGioHang adapterGioHang;
    public static List<DatMon> datMonList = new ArrayList<>();
    List<DatMon> datMonList1 = new ArrayList<>();
    MenuItem item;

    // DatMon datMon;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.layout_fragment_angi, container, false);
        recyclerViewGioHang = view.findViewById(R.id.recyclerViewGioHang);
//        gioHangContronller=new GioHangContronller(getContext());
//        gioHangContronller.getDanhSachDatMon();
        Log.d("kiemtra", datMonList + "");
        recyclerViewGioHang.clearOnChildAttachStateChangeListeners();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewGioHang.setLayoutManager(layoutManager);
        // recyclerViewGioHang.setLayoutManager(new LinearLayoutManager(context));

//        Log.d("kiemtra1","ab");
//        adapterGioHang = new AdapterGioHang(context, datMonList, R.layout.custom_layout_monan);
//        Log.d("kiemtra1","a");
//        recyclerViewGioHang.setAdapter(adapterGioHang);
//        adapterGioHang.notifyDataSetChanged();
//        Log.d("kiemtra1","abc");

        return view;
    }

    public AngiFragment() {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.botton_navigation, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_favorites:
                Log.d("kiemtra1", "Abc");
                adapterGioHang = new AdapterGioHang(context, datMonList, R.layout.custom_layout_monan);
                recyclerViewGioHang.setAdapter(adapterGioHang);
                adapterGioHang.notifyDataSetChanged();
            default:
                super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void getDanhSachDatMon() {
        // datMonList.clear();
        for (DatMon datMon : AdapterMonAn.datMonList) {
            datMonList.add(datMon);
            datMonList1.add(datMon);
//            Log.d("kiemtra", datMon.getTenMonAn());

        }
    }


}
