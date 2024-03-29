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
import android.widget.Toast;

import com.example.foody2.Adapter.AdapterGioHang;
import com.example.foody2.Adapter.AdapterLichSuMuaHang;
import com.example.foody2.Controller.LichSuOderController;
import com.example.foody2.Controller.interfaces.ChiTietlichsumuahangInterface;
import com.example.foody2.Model.KeyLichSuMuaHang;
import com.example.foody2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LichSuMuaHang extends AppCompatActivity implements ChiTietlichsumuahangInterface {
    Toolbar toolbar;
    TextView txtTieuDeToolBar;
    RecyclerView recyclerViewLichSu;
    LichSuOderController lichSuOderController;
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    DatabaseReference databaseReference;
    List<String> key;
    AdapterLichSuMuaHang adapterLichSuMuaHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_mua_hang);

        txtTieuDeToolBar = findViewById(R.id.txtTieuDeToolBar);
        txtTieuDeToolBar.setText("Lịch sử mua hàng");
        txtTieuDeToolBar = findViewById(R.id.txtTieuDeToolBar);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FirebaseUser user=firebaseAuth.getCurrentUser();
        String Uid=user.getUid();
        key=new ArrayList<>();


        recyclerViewLichSu=findViewById(R.id.recyclerViewLichSuMuaHang);
//        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);// cột true chính là đảo ngược dữ liệu và ngược lại
//        recyclerViewLichSu.setLayoutManager(layoutManager);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewLichSu.setLayoutManager(layoutManager);


        lichSuOderController=new LichSuOderController();
//        lichSuOderController.HienThiKey(Uid,recyclerViewLichSu);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("lichsuoder").child(Uid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot valuekey : dataSnapshot.getChildren()){

                  // KeyLichSuMuaHang keyLichSuMuaHang=valuekey.getValue(KeyLichSuMuaHang.class);
                    key.add(valuekey.getKey());
                    Log.d("kiemtra",key+ "");
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
                    recyclerViewLichSu.setLayoutManager(layoutManager);
                    adapterLichSuMuaHang = new AdapterLichSuMuaHang(getBaseContext(), key,LichSuMuaHang.this);
                    recyclerViewLichSu.setAdapter(adapterLichSuMuaHang);
                    adapterLichSuMuaHang.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.d("kiemtra",key+"");
    }

    @Override
    public void OntemClick(int position) {
//        Toast.makeText(this,key.get(position),Toast.LENGTH_LONG).show();
//        Log.d("kiemtrakey",key.get(position) +"");
        Intent idLichSuChiTietmuahang=new Intent(this,LichSuChiTietMuaHang.class);
        idLichSuChiTietmuahang.putExtra("makey",key.get(position));
        startActivity(idLichSuChiTietmuahang);
    }

    @Override
    public void onLogItemLick(int position) {
//        key.remove(position);
//        adapterLichSuMuaHang.notifyItemRemoved(position);
    }
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}