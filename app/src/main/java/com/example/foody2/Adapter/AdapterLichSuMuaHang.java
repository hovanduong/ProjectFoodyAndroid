package com.example.foody2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Model.KeyLichSuMuaHang;
import com.example.foody2.Model.LichSuOder;
import com.example.foody2.R;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterLichSuMuaHang extends RecyclerView.Adapter<AdapterLichSuMuaHang.ViewHolder> {
    Context context;
    List<LichSuOder> lichSuOderList;
    int resource;

    public AdapterLichSuMuaHang(Context context, List<LichSuOder> lichSuOderList, int resource){
        this.context=context;
        this.lichSuOderList=lichSuOderList;
        this.resource=resource;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtKey;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtKey=itemView.findViewById(R.id.txtKeyGioHang);

        }
    }

    @NonNull
    @Override
    public AdapterLichSuMuaHang.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        AdapterLichSuMuaHang.ViewHolder viewHolder=new AdapterLichSuMuaHang.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLichSuMuaHang.ViewHolder holder, int position) {
        LichSuOder lichSuOder= lichSuOderList.get(position);
        holder.txtKey.setText(lichSuOder.getKey());
    }

    @Override
    public int getItemCount() {
        return lichSuOderList.size();
    }


}
