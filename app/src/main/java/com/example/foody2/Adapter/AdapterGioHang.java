package com.example.foody2.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Controller.interfaces.GioHangInterface;
import com.example.foody2.Model.DatMon;
import com.example.foody2.R;

import java.util.List;

public class AdapterGioHang  extends RecyclerView.Adapter<AdapterGioHang.ViewHolder>{

    Context context;
    List<DatMon> datMons;
    int resource;
    double sum;
    public  AdapterGioHang(Context context,
            List<DatMon> datMons,
            int resource){
        this.context=context;
        this.datMons=datMons;
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
    public AdapterGioHang.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        AdapterGioHang.ViewHolder viewHolder=new AdapterGioHang.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGioHang.ViewHolder holder, int position) {
       DatMon datMon=datMons.get(position);
       holder.txtTenMonAn.setText(datMon.getTenMonAn());
       holder.txtSoLuong.setText(datMon.getSoLuong() + "");
       holder.txtGia.setText(datMon.getGia()+"đ");
//      sum=datMon.getSoLuong() * Double.parseDouble(datMon.getGia());


//       holder.txtSoLuong.setText(datMon.getSoLuong());
        //if(datMon.getTenMonAn().equals())
       // datMons.remove(position);

    }

    @Override
    public int getItemCount() {

        return datMons.size();
    }


}
