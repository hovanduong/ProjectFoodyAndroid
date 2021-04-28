package com.example.foody2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Model.ChiNhanhQuanAnModel;
import com.example.foody2.Model.QuanAnModel;
import com.example.foody2.R;


import java.util.List;


public class AdapterBlog extends RecyclerView.Adapter<AdapterBlog.ViewHolder> {
    List<QuanAnModel> quanAnModelList;
    int resource;
    Context context;


    public AdapterBlog(Context context, List<QuanAnModel> quanAnModelList, int resource) {
        this.quanAnModelList = quanAnModelList;
        this.resource = resource;
        this.context = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenQuanAnOdau;
        ImageView imageHinhQuanAnOdau;
        TextView txtDiemTrungbinh, txtDiaChiQuanAnOdau, txtKhoangCachQuanAnOdau,txtNoiDungBlog;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenQuanAnOdau = itemView.findViewById(R.id.txtTenQuanAnOdau);
            imageHinhQuanAnOdau = itemView.findViewById(R.id.imageHinhQuanAnoDau);
            txtDiemTrungbinh = itemView.findViewById(R.id.txtDiemtrungbinhquanan);
            txtDiaChiQuanAnOdau = itemView.findViewById(R.id.txtDiaChiQuanAnOdau);
            txtKhoangCachQuanAnOdau = itemView.findViewById(R.id.txtKhoangCachQuanAnOdau);
            cardView = itemView.findViewById(R.id.carViewODau);
            txtNoiDungBlog=itemView.findViewById(R.id.txtNoiDungBlog);
        }
    }

    @NonNull
    @Override
    public AdapterBlog.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        AdapterBlog.ViewHolder viewHolder = new AdapterBlog.ViewHolder(view);
        return viewHolder;
    }


    public void onBindViewHolder(@NonNull final AdapterBlog.ViewHolder holder, int position) {
        final QuanAnModel quanAnModel = quanAnModelList.get(position);

        holder.txtTenQuanAnOdau.setText(quanAnModel.getTenquanan());

        if (quanAnModel.getBitmapList().size() > 0) {
            holder.imageHinhQuanAnOdau.setImageBitmap(quanAnModel.getBitmapList().get(0));
        }
        holder.txtNoiDungBlog.setText(quanAnModel.getNoidung());
        // Lấy chi nhánh quán ăn và hiển thị địa chỉ và km
        if (quanAnModel.getChiNhanhQuanAnModelList().size() > 0) {
            ChiNhanhQuanAnModel chiNhanhQuanAnModelTam = quanAnModel.getChiNhanhQuanAnModelList().get(0);
            for (ChiNhanhQuanAnModel chiNhanhQuanAnModel : quanAnModel.getChiNhanhQuanAnModelList()) {
                if (chiNhanhQuanAnModelTam.getKhoangcach() > chiNhanhQuanAnModel.getKhoangcach()) {
                    chiNhanhQuanAnModelTam = chiNhanhQuanAnModel;
                }
            }
            holder.txtDiaChiQuanAnOdau.setText(chiNhanhQuanAnModelTam.getDiachi());
            holder.txtKhoangCachQuanAnOdau.setText(String.format("%.1f", chiNhanhQuanAnModelTam.getKhoangcach()) + " km");
        }

    }


    public int getItemCount() {
        return quanAnModelList.size();
    }


}
