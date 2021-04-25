package com.example.foody2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Model.GiaoHangModel;
import com.example.foody2.Model.QuanAnModel;
import com.example.foody2.R;

import java.util.List;

public class AdapterGiaoHang extends RecyclerView.Adapter<AdapterGiaoHang.GiaoHangViewHorder> {
     List<GiaoHangModel> listGh;
     Context context;

    public AdapterGiaoHang(List<GiaoHangModel> listGh, Context context) {
        this.listGh = listGh;
        this.context = context;
    }

    public AdapterGiaoHang() {
    }

    @NonNull
    @Override
    public GiaoHangViewHorder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cus_layout_recyclerview_giaohang,parent,false);
        return new GiaoHangViewHorder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GiaoHangViewHorder holder, int position) {
        GiaoHangModel giaoHangModel = listGh.get(position);

        holder.diachi.setText(giaoHangModel.getTenquanan());
        holder.diachi.setText(giaoHangModel.getDiachi());
    }

    @Override
    public int getItemCount() {
        return listGh.size();
    }

    public class GiaoHangViewHorder extends RecyclerView.ViewHolder{
         TextView tenquanan,diachi;

         public GiaoHangViewHorder(@NonNull View itemView) {
             super(itemView);
             tenquanan = itemView.findViewById(R.id.txtTenQuanAnOdaugh);
             diachi    = itemView.findViewById(R.id.txtDiaChiQuanAnOdaugh);
         }
     }

}
