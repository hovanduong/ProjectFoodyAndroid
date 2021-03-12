package com.example.foody2.Controller;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Adapter.AdapterThucDon;
import com.example.foody2.Controller.interfaces.ThucDonInterface;
import com.example.foody2.Model.ThucDonModel;


import java.util.List;

/**
 * Created by Binh on 7/10/17.
 */

public class ThucDonController {
    ThucDonModel thucDonModel;

    public ThucDonController(){
        thucDonModel = new ThucDonModel();
    }

    public void getDanhSachThucDonQuanAnTheoMa(final Context context, String manquanan, final RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        ThucDonInterface thucDonInterface = new ThucDonInterface() {
            @Override
            public void getThucDonThanhCong(List<ThucDonModel> thucDonModelList) {
                AdapterThucDon adapterThucDon = new AdapterThucDon(context,thucDonModelList);
                recyclerView.setAdapter(adapterThucDon);
                adapterThucDon.notifyDataSetChanged();
            }
        };
        thucDonModel.getDanhSachThucDonQuanAnTheoMa(manquanan,thucDonInterface);
    }
}
