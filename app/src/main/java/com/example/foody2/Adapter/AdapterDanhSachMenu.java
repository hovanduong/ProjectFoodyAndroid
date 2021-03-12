package com.example.foody2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Model.MenuQuanAn;
import com.example.foody2.R;
import com.example.foody2.View.GiohangActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterDanhSachMenu extends RecyclerView.Adapter<AdapterDanhSachMenu.ViewHolder> {
    Context context;
    int layout;
    List<MenuQuanAn> menuQuanAnList;
    public AdapterDanhSachMenu(Context context,int layout,List<MenuQuanAn> menuQuanAnList){
        this.context=context;
        this.layout=layout;
        this.menuQuanAnList=menuQuanAnList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView cicleMenuMonAn;
        TextView txtTenmonannMenu,txtSoluongMenu,txtGiamonan;
        ImageView imgThemmonan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cicleMenuMonAn=itemView.findViewById(R.id.cicleMenuMonAn);
            txtTenmonannMenu=itemView.findViewById(R.id.txtTenmonannMenu);
            txtSoluongMenu=itemView.findViewById(R.id.txtSoluongMenu);
            txtGiamonan=itemView.findViewById(R.id.txtGiamonan);
            imgThemmonan=itemView.findViewById(R.id.imgThemmonan);

        }
    }
    @NonNull
    @Override
    public AdapterDanhSachMenu.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        AdapterDanhSachMenu.ViewHolder viewHolder=new AdapterDanhSachMenu.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDanhSachMenu.ViewHolder holder, int position) {
            final MenuQuanAn menuQuanAn=menuQuanAnList.get(position);
            holder.txtGiamonan.setText(menuQuanAn.getGiatien());
            holder.txtSoluongMenu.setText(menuQuanAn.getSolandat());
            holder.txtTenmonannMenu.setText(menuQuanAn.getTenmon());
            holder.imgThemmonan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, GiohangActivity.class);
                    intent.putExtra("tenmonan", menuQuanAn.getTenmon());  // Truyền một String
                    intent.putExtra("giamonan", menuQuanAn.getGiatien());
                   context.startActivity(intent);
                }
            });


    }

    @Override
    public int getItemCount() {
        return menuQuanAnList.size();
    }


}
