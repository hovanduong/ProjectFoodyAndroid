package com.example.foody2.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import android.widget.Toast;

import com.example.foody2.Adapter.AdapterGioHang;
import com.example.foody2.Adapter.AdapterMonAn;
import com.example.foody2.Adapter.AdapterThucDon;
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

public class TrangChuActivity extends AppCompatActivity {

     ApdaterRecyclerOdau apdaterRecyclerOdau;
     List<QuanAnModel> quanAnModelList;
    AdapterGioHang adapterGioHang;
    //private Fragment fragmentTemp = OdauFragment.getInstance();
    final Fragment fragment1 = new OdauFragment();
    final Fragment fragment2 = new AngiFragment();
    final Fragment fragment3 = new ProfileFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        fm.beginTransaction().add(R.id.fragment_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.fragment_container,fragment1, "1").commit();

        apdaterRecyclerOdau = new ApdaterRecyclerOdau(this, quanAnModelList, R.layout.cus_layout_recyclerview_odau);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            fm.beginTransaction().hide(active).show(fragment1).commit();
                            active=fragment1;
                            return true;
                        case R.id.nav_favorites:
                            if(AdapterMonAn.datMonList.size() == 0){
                                Toast.makeText(getApplicationContext(),"Chưa có sản phẩm",Toast.LENGTH_LONG).show();
                                return  false;
                            }else{
                                fm.beginTransaction().hide(active).show(fragment2).commit();
                                active=fragment2;
                                return true;
                            }
                        case R.id.nav_profileuser:
                            fm.beginTransaction().hide(active).show(fragment3).commit();
                            active=fragment3;
                            return true;
                    }
//
                    return false;
                }
            };



}
