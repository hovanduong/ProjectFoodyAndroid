package com.example.foody2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Controller.interfaces.ChiTietlichsumuahangInterface;
import com.example.foody2.Model.KeyLichSuMuaHang;
import com.example.foody2.Model.LichSuOder;
import com.example.foody2.R;
import com.example.foody2.View.DatMonAnActivity;
import com.example.foody2.View.LichSuChiTietMuaHang;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterLichSuMuaHang extends RecyclerView.Adapter<AdapterLichSuMuaHang.ViewHolder> {
    Context context;
    List<String> key;
   // int resource;
    private ChiTietlichsumuahangInterface chiTietlichsumuahangInterface;

    public AdapterLichSuMuaHang(Context context, List<String> key,ChiTietlichsumuahangInterface chiTietlichsumuahangInterface){
        this.context=context;
        this.key=key;
       // this.resource=resource;
        this.chiTietlichsumuahangInterface=chiTietlichsumuahangInterface;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtKey,txtTenDonHang;
        LinearLayout btnlichsumuahang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtKey=itemView.findViewById(R.id.txtKeyGioHang);
            txtTenDonHang=itemView.findViewById(R.id.tenDonHang);
            btnlichsumuahang=itemView.findViewById(R.id.btnlichsumuahang);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chiTietlichsumuahangInterface.OntemClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(v -> {
//                key.remove(getAdapterPosition());
//                notifyItemChanged(getAdapterPosition());
                chiTietlichsumuahangInterface.onLogItemLick(getAdapterPosition());
                return true;
            });
        }


    }
    @NonNull
    @Override
    public AdapterLichSuMuaHang.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_lichsumuahang,parent,false);
        AdapterLichSuMuaHang.ViewHolder viewHolder=new AdapterLichSuMuaHang.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLichSuMuaHang.ViewHolder holder, int position) {

        holder.txtTenDonHang.setText("Đơn hàng: "+position);
        holder.txtKey.setText("Mã đơn hàng: "+key.get(position));
     //   LichSuOder lichSuOder= lichSuOderList.get(position);
//            String keyLichSuMuaHang=key.get(position);
//            for(int i=0;i <=key.size();i++){
//                holder.txtTenDonHang.setText("Đơn hàng số: " + position);
//                holder.txtKey.setText("Mã đơn hàng: "+keyLichSuMuaHang);
//
//            }

//        holder.btnlichsumuahang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent idchitietlichsu= new Intent(context, LichSuChiTietMuaHang.class);
//                idchitietlichsu.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                idchitietlichsu.putExtra("machitiet", holder.txtKey.getText());
//                context.startActivity(idchitietlichsu);
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if(key.size()  == 0){
            Toast.makeText(context,"Ban khong co lich su mua hang",Toast.LENGTH_LONG).show();
        }
        return key.size();
    }


}
