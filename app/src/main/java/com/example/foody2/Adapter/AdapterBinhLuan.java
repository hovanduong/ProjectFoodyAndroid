package com.example.foody2.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Model.BinhLuanModel;
import com.example.foody2.Model.QuanAnModel;
import com.example.foody2.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterBinhLuan extends RecyclerView.Adapter<AdapterBinhLuan.ViewHolder> {
    Context context;
    int layout;
    List<BinhLuanModel> binhLuanModelList;
    public AdapterBinhLuan(Context context, int layout, List<BinhLuanModel> binhLuanModelList){
        this.context=context;
        this.layout=layout;
        this.binhLuanModelList=binhLuanModelList;

    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView txtTieuDeBinhLuan,txtNoiDungBinhLuan,txtDiemHinhLuan;
        RecyclerView recyclerViewHinhAnhBinhLuan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView=itemView.findViewById(R.id.cicleImageUser);
            txtNoiDungBinhLuan=itemView.findViewById(R.id.txtNoidungbinhluan);
            txtTieuDeBinhLuan=itemView.findViewById(R.id.txtTieudebinhluan);
            txtDiemHinhLuan=itemView.findViewById(R.id.txtDiembinhluan);
            recyclerViewHinhAnhBinhLuan=itemView.findViewById(R.id.recyclerHinhAnhBinhLuan);
        }
    }
    @NonNull
    @Override
    public AdapterBinhLuan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterBinhLuan.ViewHolder holder, int position) {
        final BinhLuanModel binhLuanModel=binhLuanModelList.get(position);
        holder.txtTieuDeBinhLuan.setText(binhLuanModel.getTieude());
        holder.txtNoiDungBinhLuan.setText(binhLuanModel.getNoidung());
        holder.txtDiemHinhLuan.setText(binhLuanModel.getChamdiem() + "");
//        setHinhAnhBinhLuan(holder.circleImageView,binhLuanModel.getThanhVienModel().getHinhanh());

//        AdapterRecyclerHinhBinhLuan adapterRecyclerHinhBinhLuan=new AdapterRecyclerHinhBinhLuan(context,R.layout.custom_layout_hinhbinhluan,binhLuanModel.getHinhanhbinhluan());
//        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(context,2); // tham so la contex và số cột
//        holder.recyclerViewHinhAnhBinhLuan.setLayoutManager(layoutManager);
//        holder.recyclerViewHinhAnhBinhLuan.setAdapter(adapterRecyclerHinhBinhLuan);
//        adapterRecyclerHinhBinhLuan.notifyDataSetChanged();

        // hien thi chi tiet hinh anh binh luan ? ?? down anh xuong luu vao bitmapss
        final List<Bitmap>  bitmapList = new ArrayList<>();
        for (String linkhinh : binhLuanModel.getHinhanhbinhluan()){
            StorageReference storageHinhUser = FirebaseStorage.getInstance().getReference().child("hinhanh").child(linkhinh);
            long ONE_MEGABYTE = 1024 * 1024;
            storageHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    bitmapList.add(bitmap);
                    if(bitmapList.size() == binhLuanModel.getHinhanhbinhluan().size()){
                        // tao adapter ddeer đua hinh anh binh luan vao chi tiet hinh anh binh luan
                        // thong qua adpater
                        // truyen binh luan model qua adapter vi trong binhluanmodel có ảnh và user thông qua adapter
                        // vì đang ở phân chi tiest binh luân nền isChitietbinhluan phai trả về false (sẽ khong hiển thị hết ảnh)
                        AdapterRecyclerHinhBinhLuan adapterRecyclerHinhBinhLuan = new AdapterRecyclerHinhBinhLuan(context,R.layout.custom_layout_hinhbinhluan,bitmapList, false, binhLuanModel);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,2); // mành hình hiển thị và số cột được chia
                        holder.recyclerViewHinhAnhBinhLuan.setLayoutManager(layoutManager);
                        holder.recyclerViewHinhAnhBinhLuan.setAdapter(adapterRecyclerHinhBinhLuan);
                        adapterRecyclerHinhBinhLuan.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int sobinhluan=binhLuanModelList.size();
        if( sobinhluan > 4){
            return 4;
        }else{
            return binhLuanModelList.size();
        }

    }
    private void setHinhAnhBinhLuan(final CircleImageView circleImageView, String linkhinh){
        StorageReference storageHinhUser= FirebaseStorage.getInstance().getReference().child("thanhvien").child(linkhinh);

        final long ONE_MEGABYTE=1024 * 1024;
        storageHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                circleImageView.setImageBitmap(bitmap);
            }
        });
    }

}
