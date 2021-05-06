
package com.example.foody2.View.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Adapter.AdapterGioHang;
import com.example.foody2.Adapter.AdapterMonAn;
import com.example.foody2.Controller.LichSuOderController;
import com.example.foody2.Controller.interfaces.GioHangInterface;
import com.example.foody2.Model.DatMon;
import com.example.foody2.Model.LichSuOder;
import com.example.foody2.R;
import com.example.foody2.View.LichSuMuaHang;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class AngiFragment extends Fragment {
    //GioHangContronller gioHangContronller;
    Context context;
    private RecyclerView recyclerViewGioHang;
    AdapterGioHang adapterGioHang;
    public static List<DatMon> datMonList = new ArrayList<>();
    TextView txtTongtien;
    TextView txtTieuDeToolBar;
    Toolbar toolbar;
    int tongtien;
    Button btnThanhToan;
    FirebaseUser firebaseUser;
    LichSuOderController lichSuOderController;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    //    private static AngiFragment angiFragment = null;
//    public static AngiFragment getInstance(){
//        if (angiFragment == null){
//            angiFragment = new AngiFragment();
//            return angiFragment;
//        }return angiFragment;
//    }
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
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        txtTongtien = getView().findViewById(R.id.txtTongTien);
        btnThanhToan = getView().findViewById(R.id.btnThanhToan);
        lichSuOderController = new LichSuOderController();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        tongtien = 0;
        for (DatMon datMon : datMonList) {
            tongtien = tongtien + datMon.getSoLuong() * Integer.parseInt(datMon.getGia());
        }
        txtTongtien.setText(tongtien +"đ");
        recyclerViewGioHang = getView().findViewById(R.id.recyclerViewGioHang);
        recyclerViewGioHang.setHasFixedSize(true);
        recyclerViewGioHang.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewGioHang.setLayoutManager(layoutManager);
        adapterGioHang = new AdapterGioHang(context, datMonList, R.layout.custom_layout_monan);
        recyclerViewGioHang.setAdapter(adapterGioHang);
        adapterGioHang.notifyDataSetChanged();
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder altdial = new AlertDialog.Builder(getContext());
                altdial.setMessage("Bạn chắc chắn đồng ý thanh toán ?");
                altdial.setCancelable(true);
                altdial.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        List<LichSuOder> lichSuOders = new ArrayList<>();
                        LichSuOder lichSuOder = new LichSuOder();
                        for (DatMon datMon : datMonList) {
                            lichSuOder= new LichSuOder(datMon.getTenMonAn(),datMon.getSoLuong(),datMon.getGia());
                            lichSuOders.add(lichSuOder);
                        }


                        lichSuOderController.ThemLichsuOder(context, lichSuOders, user.getUid(), lichSuOder);
                        txtTongtien.setText(0 + "");
                        datMonList.clear();
                        AdapterMonAn.datMonList.clear();
                        adapterGioHang.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Thanh toán thành công!!", Toast.LENGTH_LONG).show();
                        Intent idLichsumuahang = new Intent(getContext(), LichSuMuaHang.class);
                        startActivity(idLichsumuahang);
                    }
                });
                altdial.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = altdial.create();
                alertDialog.setTitle("Oder Foody");
                alertDialog.show();

            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


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

        datMonList.clear();
        for (DatMon datMon : AdapterMonAn.datMonList) {
            datMonList.add(datMon);
        }

    }

}
