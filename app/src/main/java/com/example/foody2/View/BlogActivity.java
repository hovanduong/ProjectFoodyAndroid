package com.example.foody2.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.foody2.Controller.BlogController;
import com.example.foody2.Controller.OdauController;
import com.example.foody2.R;

public class BlogActivity extends AppCompatActivity {
    RecyclerView recyclerBlog;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    NestedScrollView nestedScrollView;
    SwipeRefreshLayout swiperefresh;
    BlogController blogController;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_blog);
        recyclerBlog = findViewById(R.id.recyclerBlog);
        progressBar = findViewById(R.id.progressBarOdau);
        nestedScrollView = findViewById(R.id.netsScrollODau);
        swiperefresh=findViewById(R.id.swiperefresh);
        sharedPreferences = getSharedPreferences("toado", Context.MODE_PRIVATE);
        Location vitrihientai = new Location("");

        vitrihientai.setLatitude(Double.parseDouble(sharedPreferences.getString("latitude", "0")));
        vitrihientai.setLongitude(Double.parseDouble(sharedPreferences.getString("longitude", "0")));
        blogController = new BlogController(getApplicationContext());
        blogController.getDanhSachQuanAnControllerBlog(nestedScrollView, recyclerBlog, progressBar, vitrihientai, swiperefresh);
    }

}