
package com.example.foody2.View.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
    TextView txtTongtien;
    TextView txtTieuDeToolBar;
    Toolbar toolbar;

    private static AngiFragment angiFragment = null;
    public static AngiFragment getInstance(){
        if (angiFragment == null){
            angiFragment = new AngiFragment();
            return angiFragment;
        }return angiFragment;
    }
    // DatMon datMon;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.layout_fragment_angi, container, false);
        // xử lý tool bar
        txtTieuDeToolBar = view.findViewById(R.id.txtTieuDeToolBar);
        txtTieuDeToolBar.setText("Giỏ hàng");
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


//        txtTongtien=view.findViewById(R.id.txtTongTien);
//        recyclerViewGioHang = view.findViewById(R.id.recyclerViewGioHang);
////        gioHangContronller=new GioHangContronller(getContext());
////        gioHangContronller.getDanhSachDatMon();
//        Log.d("kiemtra", datMonList + "");
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
//        recyclerViewGioHang.setLayoutManager(layoutManager);
//        adapterGioHang = new AdapterGioHang(context, datMonList, R.layout.custom_layout_monan);
//        recyclerViewGioHang.setAdapter(adapterGioHang);
        // adapterGioHang.notifyDataSetChanged();

        tongtien();

        return view;
    }

    private void tongtien() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerViewGioHang = view.findViewById(R.id.recyclerViewGioHang);
        recyclerViewGioHang.setHasFixedSize(true);
        recyclerViewGioHang.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewGioHang.setLayoutManager(layoutManager);
        adapterGioHang = new AdapterGioHang(context, datMonList, R.layout.custom_layout_monan);
        recyclerViewGioHang.setAdapter(adapterGioHang);
        adapterGioHang.notifyDataSetChanged();
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
            case R.id.nav_home:
                Log.d("kiemtra", "123");
            default:
                super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void getDanhSachDatMon() {
        // datMonList.clear();
        for (DatMon datMon : AdapterMonAn.datMonList) {
            datMonList.add(datMon);
            Log.d("kiemtraquanan",datMonList + "");

        }
    }


}
