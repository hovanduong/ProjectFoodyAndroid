package com.example.foody2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Model.BinhLuanModel;

import com.example.foody2.R;
import com.example.foody2.View.HienThiChiTietBinhLuanActivity;
import java.util.List;

public class AdapterRecyclerHinhBinhLuan extends RecyclerView.Adapter<AdapterRecyclerHinhBinhLuan.ViewHolderHinhBinhLuan> {
    // người dùng truyền vào
    Context context;
    int resource;
    List<Bitmap> listHinh;
    BinhLuanModel binhLuanModel;
    boolean isChiTietBinhLuan;
    public AdapterRecyclerHinhBinhLuan(Context context, int resource, List<Bitmap> listHinh,boolean isChiTietBinhLuan,BinhLuanModel binhLuanModel){
        this.context=context;
        this.resource=resource;
        // nhan binh luan model để lấy hinh ảnh và user
        this.binhLuanModel = binhLuanModel;
        this.listHinh = listHinh;
        this.isChiTietBinhLuan = isChiTietBinhLuan;





    }
    public class ViewHolderHinhBinhLuan extends RecyclerView.ViewHolder {
        TextView txtSoHinhAnhBinhLuan;
        ImageView imageHinhAnhBinhLuan;
        FrameLayout khungsohinhbinhluan;
        public ViewHolderHinhBinhLuan(@NonNull View itemView) {
            super(itemView);
            txtSoHinhAnhBinhLuan=itemView.findViewById(R.id.txtSoHinhBinhLuan);
            imageHinhAnhBinhLuan=itemView.findViewById(R.id.imageHinhBinhLuan);
            khungsohinhbinhluan=itemView.findViewById(R.id.khungsohinhbinhluan);
        }
    }

    @NonNull
    @Override
    public AdapterRecyclerHinhBinhLuan.ViewHolderHinhBinhLuan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        ViewHolderHinhBinhLuan viewHolder=new ViewHolderHinhBinhLuan(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerHinhBinhLuan.ViewHolderHinhBinhLuan holder, final int position) {
           // holder.txtSoHinhAnhBinhLuan
        // load hinh đúng vị trí của nó
        holder.imageHinhAnhBinhLuan.setImageBitmap(listHinh.get(position));
        //Nếu như không phải là chi tiest binh luận thi sẽ trả về true sẽ load tất cả hình ảnh ra
        if(!isChiTietBinhLuan){
            // kiem tra vi anh co phai lam tấm hình thứ 4 hay k
            // vì trong một position chính là một mảng bắt đầu từ 0 1 2 3 . Vậy ở vị trí số b3 trong position chính là 4
            if(position == 3){
                int sohinhconlai = listHinh.size()-4;
                if(sohinhconlai > 0){
                    holder.khungsohinhbinhluan.setVisibility(View.VISIBLE);
                    holder.txtSoHinhAnhBinhLuan.setText("+" + sohinhconlai);
                }

            }
        }
        holder.imageHinhAnhBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChiTietBinhLuan = new Intent(context, HienThiChiTietBinhLuanActivity.class);
                // tru
                iChiTietBinhLuan.putExtra("binhluanmodel",binhLuanModel);



                context.startActivity(iChiTietBinhLuan);
            }
        });


    }

    @Override
    public int getItemCount() {
        // hiên thị tất cả ảnh
        if(!isChiTietBinhLuan){
            if(listHinh.size() < 4){
                return listHinh.size();
            }else{
                return 4;
            }

        }else{
            return listHinh.size();
        }

    }
    }



