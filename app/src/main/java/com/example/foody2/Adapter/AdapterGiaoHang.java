package com.example.foody2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Model.QuanAnModel;
import com.example.foody2.R;

import java.util.List;

public class AdapterGiaoHang extends RecyclerView.Adapter<AdapterGiaoHang.GiaoHangViewHolder> {
     List<QuanAnModel> listGh;
     Context context;

    public AdapterGiaoHang(List<QuanAnModel> listGh, Context context) {
        this.listGh = listGh;
        this.context = context;
    }

    @NonNull
    @Override
    public GiaoHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cus_layout_recyclerview_giaohang,parent,false);
        return new GiaoHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GiaoHangViewHolder holder, int position) {
        QuanAnModel giaoHang = listGh.get(position);
        if(giaoHang == null){
            return;
        }
    }

    @Override
    public int getItemCount() {
        return listGh.size();
    }

    public class GiaoHangViewHolder extends RecyclerView.ViewHolder {


        public GiaoHangViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
