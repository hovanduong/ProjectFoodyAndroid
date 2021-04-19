package com.example.foody2.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;

import com.example.foody2.Adapter.AdapterGioHang;
import com.example.foody2.Adapter.AdapterMonAn;
import com.example.foody2.Adapter.AdapterViewPagerTrangChu;
import com.example.foody2.Adapter.ApdaterRecyclerOdau;
import com.example.foody2.Model.DatMon;
import com.example.foody2.Model.QuanAnModel;
import com.example.foody2.R;
import com.example.foody2.View.Fragments.AngiFragment;
import com.example.foody2.View.Fragments.OdauFragment;
import com.example.foody2.View.Fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class TrangChuActivity extends AppCompatActivity  {

    private ApdaterRecyclerOdau apdaterRecyclerOdau;
    private List<QuanAnModel> quanAnModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

         BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new OdauFragment()).commit();
        }
        apdaterRecyclerOdau=new ApdaterRecyclerOdau(this,quanAnModelList,R.layout.cus_layout_recyclerview_odau);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new OdauFragment();
                            break;
                        case R.id.nav_favorites:
                            selectedFragment = new AngiFragment();
                            break;
                        case R.id.nav_profileuser:
                            selectedFragment = new ProfileFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };



}
