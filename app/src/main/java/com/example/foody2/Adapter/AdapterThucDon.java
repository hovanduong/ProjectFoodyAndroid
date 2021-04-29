package com.example.foody2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Controller.interfaces.GioHangInterface;
import com.example.foody2.Model.ThucDonModel;
import com.example.foody2.R;

import java.util.List;

/**
 * Created by Binh on 7/17/17.
 */

public class AdapterThucDon extends RecyclerView.Adapter<AdapterThucDon.HolderThucDon> {

    Context context;
    List<ThucDonModel> thucDonModels;

    public AdapterThucDon(Context context, List<ThucDonModel> thucDonModels){
        this.context = context;
        this.thucDonModels = thucDonModels;
    }
    public class HolderThucDon extends RecyclerView.ViewHolder {
        TextView txtThucDon;
        RecyclerView recyclerViewMonAn;
        public HolderThucDon(View itemView) {
            super(itemView);

            txtThucDon = itemView.findViewById(R.id.txtTenThucDon);
            recyclerViewMonAn = itemView.findViewById(R.id.recyclerMonAn);
        }
    }
    @Override
    public AdapterThucDon.HolderThucDon onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout_thucdon,parent,false);
        return new HolderThucDon(view);
    }

    @Override
    public void onBindViewHolder(HolderThucDon holder, int position) {
        ThucDonModel thucDonModel = thucDonModels.get(position);
        holder.txtThucDon.setText(thucDonModel.getTenthucdon());
        holder.recyclerViewMonAn.setLayoutManager(new LinearLayoutManager(context));
        AdapterMonAn adapterMonAn = new AdapterMonAn(context,thucDonModel.getMonAnModelList());
        holder.recyclerViewMonAn.setAdapter(adapterMonAn);
        adapterMonAn.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return thucDonModels.size();
    }


}
