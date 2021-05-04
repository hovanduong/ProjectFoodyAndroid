package com.example.foody2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Model.DatMon;
import com.example.foody2.Model.LichSuOder;
import com.example.foody2.R;

import java.util.List;

public class AdapterLichSuMuaHangChiTiet extends RecyclerView.Adapter<AdapterLichSuMuaHangChiTiet.ViewHolder> {
    Context context;
    List<LichSuOder> LichSuOders;
    int resource;
    public AdapterLichSuMuaHangChiTiet( Context context,
            List<LichSuOder> LichSuOders,
            int resource){
        this.context=context;
        this.LichSuOders=LichSuOders;
        this.resource=resource;

    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenMonAn,txtGia,txtSoLuong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenMonAn = itemView.findViewById(R.id.txtTenMonAn);
            txtGia=itemView.findViewById(R.id.txtGia);
            txtSoLuong =  itemView.findViewById(R.id.txtSoLuong);
        }
    }

    @NonNull
    @Override
    public AdapterLichSuMuaHangChiTiet.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        AdapterLichSuMuaHangChiTiet.ViewHolder viewHolder=new AdapterLichSuMuaHangChiTiet.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLichSuMuaHangChiTiet.ViewHolder holder, int position) {
            LichSuOder lichSuOder=LichSuOders.get(position);
            holder.txtGia.setText(lichSuOder.getGiasanpham());
            holder.txtSoLuong.setText(lichSuOder.getSoluong()+"");
            holder.txtTenMonAn.setText(lichSuOder.getTensp());
    }

    @Override
    public int getItemCount() {
        return LichSuOders.size();
    }


}
