package com.example.foody2.View.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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

public class OdauFragment extends Fragment {
    OdauController odauController;
    RecyclerView recyclerOdau;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    NestedScrollView nestedScrollView;
    SwipeRefreshLayout swiperefresh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.layout_fragment_odau,container,false);
      recyclerOdau =view.findViewById(R.id.recyclerOdau);
      progressBar=view.findViewById(R.id.progressBarOdau);
      nestedScrollView=view.findViewById(R.id.netsScrollODau);
      swiperefresh=view.findViewById(R.id.swiperefresh);
      return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onStart() {
        super.onStart();
        // Fragment dùng getCOntexxt để add Vào ACtivity
        sharedPreferences=getContext().getSharedPreferences("toado", Context.MODE_PRIVATE);
        Location vitrihientai=new Location("");
        vitrihientai.setLatitude(Double.parseDouble(sharedPreferences.getString("latitude","0")));
        vitrihientai.setLongitude(Double.parseDouble(sharedPreferences.getString("longitude","0")));
        odauController =new OdauController(getContext());
        odauController.getDanhSachQuanAnController(nestedScrollView,recyclerOdau,progressBar,vitrihientai,swiperefresh);

    }
}
