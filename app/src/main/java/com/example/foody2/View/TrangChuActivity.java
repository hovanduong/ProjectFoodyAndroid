package com.example.foody2.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.foody2.Adapter.AdapterViewPagerTrangChu;
import com.example.foody2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TrangChuActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    ViewPager viewPagerTrangChu;
    RadioButton rd_odau,rd_angi;
        RadioGroup groupAngiODau;
        ImageView btnlogout;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        viewPagerTrangChu=findViewById(R.id.viewpager_trangchu);
        rd_odau=findViewById(R.id.rd_odau);
        rd_angi=findViewById(R.id.rd_angi);
        groupAngiODau=findViewById(R.id.group_odau_agni);
        btnlogout=findViewById(R.id.btnLogout);
        AdapterViewPagerTrangChu adapterViewPagerTrangChu=new AdapterViewPagerTrangChu(getSupportFragmentManager());
        viewPagerTrangChu.setAdapter(adapterViewPagerTrangChu);
        viewPagerTrangChu.addOnPageChangeListener(this);
        groupAngiODau.setOnCheckedChangeListener(this);
        btnlogout.setOnClickListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                rd_odau.setChecked(true);
                break;
            case 1:
                rd_angi.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rd_odau:
                viewPagerTrangChu.setCurrentItem(0);
            break;
            case R.id.rd_angi:
                viewPagerTrangChu.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        user= FirebaseAuth.getInstance().getCurrentUser();

       if(user != null){
           Intent idLogin=new Intent(this,DangNhapActivity.class);
           startActivity(idLogin);
           finish();
       }

    }
}
