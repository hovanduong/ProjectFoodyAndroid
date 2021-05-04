package com.example.foody2.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.example.foody2.Adapter.AdapterGioHang;
import com.example.foody2.Adapter.AdapterLichSuMuaHangChiTiet;
import com.example.foody2.Controller.interfaces.ChiTietlichsumuahangInterface;
import com.example.foody2.Model.LichSuOder;
import com.example.foody2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LichSuChiTietMuaHang extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtTieuDeToolBar;
    String makey;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    AdapterLichSuMuaHangChiTiet adapterLichSuMuaHangChiTiet;
    private RecyclerView recycler_chitietlichsumuahang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_chi_tiet_mua_hang);

        txtTieuDeToolBar = findViewById(R.id.txtTieuDeToolBar);
        txtTieuDeToolBar.setText("Lịch sử mua hàng");
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FirebaseUser user=firebaseAuth.getCurrentUser();
        String Uid=user.getUid();
        Intent intent=getIntent();
        List<LichSuOder> lichSuOders=new ArrayList<>();
        makey=intent.getStringExtra("makey");
        databaseReference= FirebaseDatabase.getInstance().getReference().child("lichsuoder").child(Uid).child(makey);

        recycler_chitietlichsumuahang = findViewById(R.id.recycler_chitietlichsumuahang);


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    GenericTypeIndicator<Map<String, List<LichSuOder>>> genericTypeIndicator = new GenericTypeIndicator<Map<String, List<LichSuOder>>>() {};
                    Map<String, List<LichSuOder>> hashMap = dataSnapshot.getValue(genericTypeIndicator);
                    for (Map.Entry<String,List<LichSuOder>> entry : hashMap.entrySet()) {
                        List<LichSuOder> educations = entry.getValue();
                        for (LichSuOder education: educations){
                            lichSuOders.add(education);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
                            recycler_chitietlichsumuahang.setLayoutManager(layoutManager);
                            adapterLichSuMuaHangChiTiet = new AdapterLichSuMuaHangChiTiet(getBaseContext(), lichSuOders, R.layout.custom_layout_lichsuchitiet);
                            recycler_chitietlichsumuahang.setAdapter(adapterLichSuMuaHangChiTiet);
                            adapterLichSuMuaHangChiTiet.notifyDataSetChanged();
                        }
                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}