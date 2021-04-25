package com.example.foody2.Controller;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Build;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.RequiresApi;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.foody2.Adapter.ApdaterRecyclerOdau;
import com.example.foody2.Controller.interfaces.OdauInterface;
import com.example.foody2.Model.QuanAnModel;
import com.example.foody2.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class OdauController {
    Context context;
    ApdaterRecyclerOdau apdaterRecyclerOdau;
    QuanAnModel quanAnModel;
    int itemdaco = 3;

    public OdauController(Context context) {
        this.context = context;
        quanAnModel = new QuanAnModel();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getDanhSachQuanAnController(final NestedScrollView nestedScrollView, final RecyclerView recyclerOdau, final ProgressBar progressBar, final Location vitrihientai, final SwipeRefreshLayout swiperefresh) {
        final List<QuanAnModel> quanAnModelList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerOdau.setLayoutManager(layoutManager);
        apdaterRecyclerOdau = new ApdaterRecyclerOdau(context, quanAnModelList, R.layout.cus_layout_recyclerview_odau);
        recyclerOdau.setAdapter(apdaterRecyclerOdau);
        progressBar.setVisibility(View.VISIBLE);
        final OdauInterface odauInterface = new OdauInterface() {
            @Override
            public void getDanhSachQuanAnModel(final QuanAnModel quanAnModel) {
                final List<Bitmap> bitmaps = new ArrayList<>();
                for (String linkhinh : quanAnModel.getHinhanhquanan()) {
                    StorageReference storageHinhAnh = FirebaseStorage.getInstance().getReference().child("hinhanh").child(linkhinh);
                    final long ONE_MEGABYTE = 1024 * 1024 * 5;
                    storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            bitmaps.add(bitmap);
                            quanAnModel.setBitmapList(bitmaps);
                            if (quanAnModel.getBitmapList().size() == quanAnModel.getHinhanhquanan().size()) {
                                quanAnModelList.add(quanAnModel);
                                apdaterRecyclerOdau.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }


            }

        };
        //cap nhật lai dữ liệu thông qua swiperefresh
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swiperefresh.setRefreshing(false);
                getDanhSachQuanAnController(nestedScrollView, recyclerOdau, progressBar, vitrihientai, swiperefresh);
            }
        });
        // lắng nghe sự kiện roll khi cuối cùng dùng để load more
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (v.getChildAt(v.getChildCount() - 1) != null) {
                    if (scrollY >= (v.getChildAt(v.getChildCount() - 1)).getMeasuredHeight() - v.getMeasuredHeight() && scrollY > oldScrollY) {
                        // kiem tra thăng con ở vị trí cuối cùng có tồn tại k (bắt đầu từ 0 vi nó là mảng
                        itemdaco += 3;
                        quanAnModel.getDanhSachQuanAn(odauInterface, vitrihientai, itemdaco, itemdaco - 3);
                    }
                }
            }
        });
        quanAnModel.getDanhSachQuanAn(odauInterface, vitrihientai, itemdaco, 0);

    }
}



