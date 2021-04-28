package com.example.foody2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foody2.Model.DealModel;
import com.example.foody2.R;

import java.util.List;

public class AdapterDeal extends RecyclerView.Adapter<AdapterDeal.ViewHolder>  {
    Context context;
    List<DealModel> dealModelList;
    int resource;

    public AdapterDeal( Context context, List<DealModel> dealModelList, int resource){
        this.context=context;
        this.dealModelList=dealModelList;
        this.resource=resource;

    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageDealrecyvlerview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageDealrecyvlerview=itemView.findViewById(R.id.imageDealrecyvlerview);
        }
    }

    @NonNull
    @Override
    public AdapterDeal.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        AdapterDeal.ViewHolder viewHolder = new AdapterDeal.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDeal.ViewHolder holder, int position) {
        DealModel dealModel=dealModelList.get(position);
        Glide.with(context).load(dealModel.getLinkImage()).into(holder.imageDealrecyvlerview);
    }

    @Override
    public int getItemCount() {
        return dealModelList.size();
    }


}
