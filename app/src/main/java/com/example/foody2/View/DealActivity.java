package com.example.foody2.View;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.foody2.R;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.MalformedURLException;
import java.net.URL;

public class DealActivity extends AppCompatActivity {
    private ImageView imgdeal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);
        imgdeal=findViewById(R.id.imgeDeal);
        String sale1="https://st.depositphotos.com/1000260/1993/i/950/depositphotos_19939511-stock-photo-close-up-make-up-with.jpg";
        String sale2="";
        String sale3="";
        String sale4="";
        String sale5="";
        String sale6="";
        String sale7="";
        String sale8="";
        Glide.with(this).load(sale1).into(imgdeal);

    }

}