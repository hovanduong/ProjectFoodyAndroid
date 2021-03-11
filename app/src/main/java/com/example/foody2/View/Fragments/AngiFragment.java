package com.example.foody2.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Controller.GioHangContronller;
import com.example.foody2.R;

public class AngiFragment extends Fragment {
    GioHangContronller gioHangContronller;
    RecyclerView recyclerViewGioHang;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment_angi,container,false);
        recyclerViewGioHang=view.findViewById(R.id.recyclerViewGioHang);
        gioHangContronller=new GioHangContronller(getContext());
        gioHangContronller.getDanhSachDatMon(recyclerViewGioHang);
        return view;
    }
}
