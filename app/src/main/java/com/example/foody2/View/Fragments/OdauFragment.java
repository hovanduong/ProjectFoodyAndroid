package com.example.foody2.View.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.foody2.Controller.OdauController;
import com.example.foody2.Model.QuanAnModel;
import com.example.foody2.R;
import com.example.foody2.View.BlogActivity;
import com.example.foody2.View.LuckyWheel;
import com.example.foody2.activities.chat.ChatActivity;

public class OdauFragment extends Fragment implements View.OnClickListener {
    OdauController odauController;
    RecyclerView recyclerOdau;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    NestedScrollView nestedScrollView;
    SwipeRefreshLayout swiperefresh;
    Button btnLuckyWheel, btnChat,btnBlog;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_odau, container, false);
        recyclerOdau = view.findViewById(R.id.recyclerOdau);
        recyclerOdau.setHasFixedSize(true);
        progressBar = view.findViewById(R.id.progressBarOdau);
        nestedScrollView = view.findViewById(R.id.netsScrollODau);
        swiperefresh = view.findViewById(R.id.swiperefresh);
        btnLuckyWheel = view.findViewById(R.id.btnLuckyWheel);
        btnLuckyWheel.setOnClickListener(this);
        btnChat = view.findViewById(R.id.btnChat);
        btnChat.setOnClickListener(this);
        btnBlog=view.findViewById(R.id.btnblog);
        btnBlog.setOnClickListener(this);
        // Fragment dùng getCOntexxt để add Vào ACtivity
        sharedPreferences = getContext().getSharedPreferences("toado", Context.MODE_PRIVATE);
        Location vitrihientai = new Location("");
        vitrihientai.setLatitude(Double.parseDouble(sharedPreferences.getString("latitude", "0")));
        vitrihientai.setLongitude(Double.parseDouble(sharedPreferences.getString("longitude", "0")));
        odauController = new OdauController(getContext());
        odauController.getDanhSachQuanAnController(nestedScrollView, recyclerOdau, progressBar, vitrihientai, swiperefresh);
        setHasOptionsMenu(true);

        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnLuckyWheel:
                Intent idLuckyWheel = new Intent(getContext(), LuckyWheel.class);
                startActivity(idLuckyWheel);
                break;
            case R.id.btnChat:
                Intent idChat = new Intent(getContext(), ChatActivity.class);
                startActivity(idChat);
                break;
            case R.id.btnblog:
                Intent idBlog = new Intent(getContext(), BlogActivity.class);
                startActivity(idBlog);
                break;
        }


    }


}
