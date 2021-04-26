package com.example.foody2.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.foody2.Adapter.AdapterDeal;
import com.example.foody2.Model.DealModel;
import com.example.foody2.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class DealMain extends AppCompatActivity {

    RecyclerView recview;
    List<DealModel> list;

    AdapterDeal adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_main);

        recview = (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 3);

        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        FirebaseRecyclerOptions<DealModel> options = new FirebaseRecyclerOptions.Builder<DealModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("hinhanhdeal"), DealModel.class)
                .build();
        adapter = new AdapterDeal(options);
        recview.setLayoutManager(linearLayoutManager);

        recview.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}