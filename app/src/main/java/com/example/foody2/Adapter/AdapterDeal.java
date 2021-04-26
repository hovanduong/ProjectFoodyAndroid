package com.example.foody2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foody2.Model.DealModel;
import com.example.foody2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterDeal extends FirebaseRecyclerAdapter<DealModel,AdapterDeal.dealholder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterDeal(@NonNull FirebaseRecyclerOptions<DealModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull dealholder dealholder, int i, @NonNull DealModel dealModel) {
       dealholder.nameImg.setText(dealModel.getMagiam());
       dealholder.address.setText(dealModel.getDiadiem());
       Glide.with(dealholder.img.getContext()).load(dealModel.getHinh()).into(dealholder.img);
    }

    @NonNull
    @Override
    public dealholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cus_deal,parent,false);
        return new dealholder(view);
    }

    public  class dealholder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView nameImg,address;

        public dealholder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.img1);
            nameImg =(TextView)itemView.findViewById(R.id.nametext);
            address = (TextView)itemView.findViewById(R.id.address);
        }
    }

}
