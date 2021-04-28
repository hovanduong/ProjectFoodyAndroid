package com.example.foody2.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.foody2.Adapter.AdapterDeal;
import com.example.foody2.Model.DealModel;
import com.example.foody2.R;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DealActivity extends AppCompatActivity {


    private RecyclerView recycler_view_deal,recycler_view_deal1;
    AdapterDeal adapterDeal;
    AdapterDeal adapterDealFreeShip;
    List<DealModel> dealModelList;
    List<DealModel> dealModelListFreeShip;
    ImageView imgeDeal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);
        recycler_view_deal=findViewById(R.id.recycler_view_deal);
        recycler_view_deal1=findViewById(R.id.recycler_view_deal1);
        imgeDeal=findViewById(R.id.imgeDeal);
        String sale="https://st.depositphotos.com/1000260/1993/i/950/depositphotos_19939511-stock-photo-close-up-make-up-with.jpg";
        String sale1="https://images.foody.vn/delivery/collection/s320x200/image-62c7b562-210412233654.png";
        String sale2="https://images.foody.vn/delivery/collection/s320x200/image-6845cf0c-210420180949.jpeg";
        String sale3="https://images.foody.vn/delivery/collection/s320x200/image-a51b51db-210420231911.jpeg";
        String sale4="https://images.foody.vn/delivery/collection/s320x200/image-2d40fc7f-210315114035.jpeg";
        String sale5="https://images.foody.vn/delivery/collection/s320x200/image-c0d67881-210115101815.jpeg";
        String sale6="https://images.foody.vn/delivery/collection/s480x300/image-13ddbbec-210104154111.jpeg";
        String sale7="https://images.foody.vn/delivery/collection/s480x300/image-363a5748-210129133551.jpeg";
        String sale8="https://images.foody.vn/delivery/collection/s480x300/image-c0d67881-210115101815.jpeg";
        String sale10="https://images.foody.vn/delivery/collection/s480x300/beauty-upload-api-image-200728010447.jpeg";
        String sale11="";
        String sale12="";
        String sale13="";

        Glide.with(this).load(sale).into(imgeDeal);
        dealModelList=new ArrayList<>();
        dealModelList.add(new DealModel(sale1));
        dealModelList.add(new DealModel(sale2));
        dealModelList.add(new DealModel(sale3));
        dealModelList.add(new DealModel(sale4));
        dealModelList.add(new DealModel(sale5));

        dealModelListFreeShip=new ArrayList<>();
        dealModelListFreeShip.add(new DealModel(sale6));
        dealModelListFreeShip.add(new DealModel(sale7));
        dealModelListFreeShip.add(new DealModel(sale8));
        dealModelListFreeShip.add(new DealModel(sale10));

        adapterDeal=new AdapterDeal(this,dealModelList,R.layout.item_deal);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recycler_view_deal.setLayoutManager(linearLayoutManager);
        recycler_view_deal.setAdapter(adapterDeal);
        adapterDeal.notifyDataSetChanged();

        adapterDealFreeShip=new AdapterDeal(this,dealModelListFreeShip,R.layout.item_deal);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recycler_view_deal1.setLayoutManager(linearLayoutManager1);
        recycler_view_deal1.setAdapter(adapterDealFreeShip);
        adapterDeal.notifyDataSetChanged();
    }
    }

