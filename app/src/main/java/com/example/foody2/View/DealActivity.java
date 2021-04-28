package com.example.foody2.View;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

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
        imgdeal.setImageDrawable(LoadImageFromWebOperations("https://st3.depositphotos.com/9880800/13074/i/1600/depositphotos_130740248-stock-photo-sale-sign-with-gift-boxes.jpg"));
    }
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}