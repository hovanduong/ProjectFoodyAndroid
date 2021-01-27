package com.example.foody2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Model.WiFiQuanAnModel;
import com.example.foody2.R;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterDanhSachWifi extends RecyclerView.Adapter<AdapterDanhSachWifi.ViewHolderWifi> {
    Context context;
    int resource;
    List<WiFiQuanAnModel> wiFiQuanAnModelList;


    public  AdapterDanhSachWifi(Context context,int resource,List<WiFiQuanAnModel> wiFiQuanAnModelList){
        this.context=context;
        this.resource=resource;
        this.wiFiQuanAnModelList=wiFiQuanAnModelList;

    }
    public class ViewHolderWifi extends RecyclerView.ViewHolder {
        TextView txtTenWifi,txtMatKhauWifi,txtNgayDangWifi;
        public ViewHolderWifi(@NonNull View itemView) {
            super(itemView);
            txtTenWifi=itemView.findViewById(R.id.txtTenWifi);
            txtMatKhauWifi=itemView.findViewById(R.id.txtMatKhauWifi);
            txtNgayDangWifi=itemView.findViewById(R.id.txtNgayDangWifi);

        }
    }
    @NonNull
    @Override
    public AdapterDanhSachWifi.ViewHolderWifi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(resource,parent,false);
        ViewHolderWifi viewHolderWifi=new ViewHolderWifi(view);
        return  viewHolderWifi;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDanhSachWifi.ViewHolderWifi holder, int position) {
        WiFiQuanAnModel wiFiQuanAnModel=wiFiQuanAnModelList.get(position);
        holder.txtTenWifi.setText(wiFiQuanAnModel.getTen());
        holder.txtMatKhauWifi.setText(wiFiQuanAnModel.getMatkhau());
        holder.txtNgayDangWifi.setText(wiFiQuanAnModel.getNgaydang());
    }

    @Override
    public int getItemCount() {
        return wiFiQuanAnModelList.size();
    }


}
