package com.example.foody2.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foody2.Controller.LichSuOderController;
import com.example.foody2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LichSuMuaHang extends AppCompatActivity {
    RecyclerView recyclerViewLichSu;
    String Uid;
    LichSuOderController lichSuOderController;
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_mua_hang);
        FirebaseUser user=firebaseAuth.getCurrentUser();
        String Uid=user.getUid();
        recyclerViewLichSu=findViewById(R.id.recyclerViewLichSuMuaHang);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);// cột true chính là đảo ngược dữ liệu và ngược lại
        recyclerViewLichSu.setLayoutManager(layoutManager);

        lichSuOderController=new LichSuOderController();
        lichSuOderController.HienThiKey(Uid,recyclerViewLichSu);

    }
}