package com.example.foody2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Model.BinhLuanModel;
import com.example.foody2.Model.ChiNhanhQuanAnModel;
import com.example.foody2.Model.QuanAnModel;
import com.example.foody2.R;
import com.example.foody2.View.ChiTietQuanAnActivity;

import com.example.foody2.View.DatMonAnActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ApdaterRecyclerOdau extends RecyclerView.Adapter<ApdaterRecyclerOdau.ViewHolder> implements Filterable {
    List<QuanAnModel> quanAnModelList;
    int resource;
    Context context;
     List<QuanAnModel> quanAnModelListFull;

    public ApdaterRecyclerOdau(Context context, List<QuanAnModel> quanAnModelList, int resource) {
        this.quanAnModelList = quanAnModelList;
        this.resource = resource;
        this.context = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenQuanAnOdau;
        Button btnDatMonOdau;
        ImageView imageHinhQuanAnOdau;
        CircleImageView cicleImageUser, cicleImageUser2;
        TextView txtTieudebinhluan, txtTieudebinhluan2, txtNoidungbinhluan, txtNoidungbinhluan2,
                txtDiemBinhLuan, txtDiemBinhLuan2, txtTongbinhluan, txtTonghinhanhbinhluan, txtDiemTrungbinh, txtDiaChiQuanAnOdau, txtKhoangCachQuanAnOdau;
        LinearLayout containerBinhLuan, containerBinhLuan2;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenQuanAnOdau = itemView.findViewById(R.id.txtTenQuanAnOdau);
            btnDatMonOdau = itemView.findViewById(R.id.btnDatMonOdau);
            imageHinhQuanAnOdau = itemView.findViewById(R.id.imageHinhQuanAnoDau);
            txtTieudebinhluan = itemView.findViewById(R.id.txtTieudebinhluan);
            txtTieudebinhluan2 = itemView.findViewById(R.id.txtTieudebinhluan2);
            txtNoidungbinhluan = itemView.findViewById(R.id.txtNoidungbinhluan);
            txtNoidungbinhluan2 = itemView.findViewById(R.id.txtNoidungbinhluan2);
            cicleImageUser = itemView.findViewById(R.id.cicleImageUser);
            cicleImageUser2 = itemView.findViewById(R.id.cicleImageUser2);
            containerBinhLuan = itemView.findViewById(R.id.containerBinhLuan);
            containerBinhLuan2 = itemView.findViewById(R.id.containerBinhLuan2);
            txtDiemBinhLuan = itemView.findViewById(R.id.txtDiembinhluan);
            txtDiemBinhLuan2 = itemView.findViewById(R.id.txtDiembinhluan2);
            txtTongbinhluan = itemView.findViewById(R.id.txtTongbinhluan);
            txtTonghinhanhbinhluan = itemView.findViewById(R.id.txtTonghinhanhbinhluan);
            txtDiemTrungbinh = itemView.findViewById(R.id.txtDiemtrungbinhquanan);
            txtDiaChiQuanAnOdau = itemView.findViewById(R.id.txtDiaChiQuanAnOdau);
            txtKhoangCachQuanAnOdau = itemView.findViewById(R.id.txtKhoangCachQuanAnOdau);
            cardView = itemView.findViewById(R.id.carViewODau);
        }
    }

    @NonNull
    @Override
    public ApdaterRecyclerOdau.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ApdaterRecyclerOdau.ViewHolder holder, int position) {
        final QuanAnModel quanAnModel = quanAnModelList.get(position);

        holder.txtTenQuanAnOdau.setText(quanAnModel.getTenquanan());
        if (quanAnModel.isGiaohang()) {
            holder.btnDatMonOdau.setVisibility(View.VISIBLE);
        }
        if (quanAnModel.getBitmapList().size() > 0) {
            holder.imageHinhQuanAnOdau.setImageBitmap(quanAnModel.getBitmapList().get(0));

        }
        // lấy dánh sách bình luận quán ăn
        if (quanAnModel.getBinhluanModeList().size() > 0) {
            BinhLuanModel binhLuanModel = quanAnModel.getBinhluanModeList().get(0);
            holder.txtTieudebinhluan.setText(binhLuanModel.getTieude());
            holder.txtNoidungbinhluan.setText(binhLuanModel.getNoidung());
            holder.txtDiemBinhLuan.setText(binhLuanModel.getChamdiem() + "");


//            if(binhLuanModel.getThanhVienModel().getHinhanh() != null){
//                setHinhAnhBinhLuan(holder.cicleImageUser,binhLuanModel.getThanhVienModel().getHinhanh());
//            }

            if (quanAnModel.getBinhluanModeList().size() > 1) {
                BinhLuanModel binhLuanModel2 = quanAnModel.getBinhluanModeList().get(1);
                holder.txtTieudebinhluan2.setText(binhLuanModel2.getTieude());
                holder.txtNoidungbinhluan2.setText(binhLuanModel2.getNoidung());
                holder.txtDiemBinhLuan2.setText(binhLuanModel2.getChamdiem() + "");

                //  setHinhAnhBinhLuan(holder.cicleImageUser2,binhLuanModel2.getThanhVienModel().getHinhanh());
            }
            holder.txtTongbinhluan.setText(quanAnModel.getBinhluanModeList().size() + "");
            int tongsohinhbinhluan = 0;
            double tongdiem = 0;


            // Tính tổng điểm của bình luận và đếm tổng số hình của bình luận
            for (BinhLuanModel binhLuanModel1 : quanAnModel.getBinhluanModeList()) {
                tongsohinhbinhluan += binhLuanModel1.getHinhanhbinhluan().size();
                tongdiem += binhLuanModel1.getChamdiem();
            }
            double diemtrungbinh = tongdiem / quanAnModel.getBinhluanModeList().size();
            holder.txtDiemTrungbinh.setText(String.format("%.1f", diemtrungbinh));
            if (tongsohinhbinhluan > 0) {
                holder.txtTonghinhanhbinhluan.setText(tongsohinhbinhluan + " km");
            }
        } else {
            holder.containerBinhLuan.setVisibility(View.GONE);
            holder.containerBinhLuan2.setVisibility(View.GONE);
        }
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
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChiTietQuanAn = new Intent(context, ChiTietQuanAnActivity.class);
                iChiTietQuanAn.putExtra("quanan", quanAnModel);
                context.startActivity(iChiTietQuanAn);
            }
        });
        holder.btnDatMonOdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent idMenuQuanAn = new Intent(context, DatMonAnActivity.class);
                idMenuQuanAn.putExtra("quanan1", quanAnModel);
                context.startActivity(idMenuQuanAn);
            }
        });
    }

    private void setHinhAnhBinhLuan(final CircleImageView circleImageView, String linkhinh) {
        StorageReference storageHinhUser = FirebaseStorage.getInstance().getReference().child("thanhvien").child(linkhinh);

        final long ONE_MEGABYTE = 1024 * 1024;
        storageHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                circleImageView.setImageBitmap(bitmap);
            }
        });
    }

    public int getItemCount() {
        return quanAnModelList.size();
    }

    @Override
    public Filter getFilter() {
        return quanAnModeFiltert;
    }

    private Filter quanAnModeFiltert = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<QuanAnModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(quanAnModelListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (QuanAnModel item : quanAnModelListFull) {
                    if (item.getTenquanan().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            quanAnModelList.clear();
            quanAnModelList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}

