package com.example.foody2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Model.KeyLichSuMuaHang;
import com.example.foody2.Model.LichSuOder;
import com.example.foody2.R;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterLichSuMuaHang extends RecyclerView.Adapter<AdapterLichSuMuaHang.ViewHolder> {
    Context context;
    List<String> key;
    int resource;

    public AdapterLichSuMuaHang(Context context, List<String> key, int resource){
        this.context=context;
        this.key=key;
        this.resource=resource;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtKey,txtTenDonHang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtKey=itemView.findViewById(R.id.txtKeyGioHang);
            txtTenDonHang=itemView.findViewById(R.id.tenDonHang);
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
     //   LichSuOder lichSuOder= lichSuOderList.get(position);
            String keyLichSuMuaHang=key.get(position);
            for(int i=0;i <=key.size();i++){
                holder.txtTenDonHang.setText("Đơn hàng số: " + position);
                holder.txtKey.setText("Mã đơn hàng: "+keyLichSuMuaHang);

            }

    }

    @Override
    public int getItemCount() {
        if(key.size()  == 0){
            Toast.makeText(context,"Ban khong co lich su mua hang",Toast.LENGTH_LONG).show();
        }
        return key.size();
    }


}
