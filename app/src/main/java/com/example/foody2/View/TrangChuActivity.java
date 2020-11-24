package com.example.foody2.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.foody2.Adapter.AdapterViewPagerTrangChu;
import com.example.foody2.R;

public class TrangChuActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    ViewPager viewPagerTrangChu;
    RadioButton rd_odau,rd_angi;
        RadioGroup groupAngiODau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        viewPagerTrangChu=findViewById(R.id.viewpager_trangchu);
        rd_odau=findViewById(R.id.rd_odau);
        rd_angi=findViewById(R.id.rd_angi);
        groupAngiODau=findViewById(R.id.group_odau_agni);
        AdapterViewPagerTrangChu adapterViewPagerTrangChu=new AdapterViewPagerTrangChu(getSupportFragmentManager());
        viewPagerTrangChu.setAdapter(adapterViewPagerTrangChu);
        viewPagerTrangChu.addOnPageChangeListener(this);
        groupAngiODau.setOnCheckedChangeListener(this);
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
}
