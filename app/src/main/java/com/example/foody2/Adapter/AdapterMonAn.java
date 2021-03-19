package com.example.foody2.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Model.DatMon;
import com.example.foody2.Model.MonAnModel;
import com.example.foody2.R;
import com.example.foody2.View.Fragments.AngiFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Binh on 7/17/17.
 */

public class AdapterMonAn extends RecyclerView.Adapter<AdapterMonAn.HolderMonAn> {
//    GioHangContronller gioHangContronller;
    AngiFragment angiFragment;


    Context context;
    List<MonAnModel> monAnModelList;
     public static List<DatMon> datMonList = new ArrayList<>();

    public AdapterMonAn(Context context, List<MonAnModel> monAnModelList){
        this.context = context;
        this.monAnModelList = monAnModelList;

    }

    public class HolderMonAn extends RecyclerView.ViewHolder {
        TextView txtTenMonAn,txtSoLuong,txtGia;
        ImageView imgGiamSoLuong,imgTangSoLuong;
        RecyclerView recyclerView;

        public HolderMonAn(View itemView) {
            super(itemView);
            txtTenMonAn = itemView.findViewById(R.id.txtTenMonAn);
            txtGia=itemView.findViewById(R.id.txtGia);
            txtSoLuong =  itemView.findViewById(R.id.txtSoLuong);
            imgGiamSoLuong =  itemView.findViewById(R.id.imgGiamSoLuong);
            imgTangSoLuong =  itemView.findViewById(R.id.imgTangSoLuong);
            recyclerView=itemView.findViewById(R.id.recyclerViewGioHang);
        }
    }

    @Override
    public HolderMonAn onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout_monan,parent,false);
        return new HolderMonAn(view);
    }

    @Override
    public void onBindViewHolder(final HolderMonAn holder, int position) {
  //      @NonNull final LayoutInflater inflater = null; @Nullable final ViewGroup container=null; @Nullable final Bundle savedInstanceState=null;
        final MonAnModel monAnModel = monAnModelList.get(position);
        holder.txtTenMonAn.setText(monAnModel.getTenmon());
        holder.txtGia.setText(monAnModel.getGiatien() + "đ");

        holder.txtSoLuong.setTag(0);
        holder.imgTangSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dem = Integer.parseInt(holder.txtSoLuong.getTag().toString());
                dem++;
                holder.txtSoLuong.setText(dem+"");
                holder.txtSoLuong.setTag(dem);

                DatMon datMonTag = (DatMon) holder.imgGiamSoLuong.getTag();
                if(datMonTag != null){
                    AdapterMonAn.datMonList.remove(datMonTag);
                }

                DatMon datMon = new DatMon();
                datMon.setSoLuong(dem);
                datMon.setTenMonAn(monAnModel.getTenmon());

                holder.imgGiamSoLuong.setTag(datMon);

                AdapterMonAn.datMonList.add(datMon);


//                gioHangContronller=new GioHangContronller(context);
//                gioHangContronller.getDanhSachDatMon();
                angiFragment=new AngiFragment();
                angiFragment.getDanhSachDatMon();
//                angiFragment.onCreateView(inflater,container,savedInstanceState);


            }
        });

        holder.imgGiamSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dem = Integer.parseInt(holder.txtSoLuong.getTag().toString());
                if(dem != 0){
                    dem--;
                    if(dem == 0){
                        DatMon datMon = (DatMon) v.getTag();
                   AdapterMonAn.datMonList.remove(datMon);
                    }
                }

                holder.txtSoLuong.setText(dem+"");
                holder.txtSoLuong.setTag(dem);

            }
        });
    }

    @Override
    public int getItemCount() {
        return monAnModelList.size();
    }


}
