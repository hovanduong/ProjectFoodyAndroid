package com.example.foody2.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.foody2.View.Fragments.AngiFragment;
import com.example.foody2.View.Fragments.OdauFragment;

public class AdapterViewPagerTrangChu extends FragmentPagerAdapter {
    AngiFragment angiFragment;
    OdauFragment odauFragment;
    public AdapterViewPagerTrangChu(@NonNull FragmentManager fm) {
        super(fm);
         angiFragment=new AngiFragment();
         odauFragment=new OdauFragment();

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return odauFragment;

            case 1:
               return angiFragment;
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
