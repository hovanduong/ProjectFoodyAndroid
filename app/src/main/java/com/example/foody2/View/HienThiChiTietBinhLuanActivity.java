package com.example.foody2.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.foody2.Adapter.AdapterRecyclerHinhBinhLuan;
import com.example.foody2.Model.BinhLuanModel;
import com.example.foody2.Model.QuanAnModel;
import com.example.foody2.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HienThiChiTietBinhLuanActivity extends AppCompatActivity {
    CircleImageView circleImageView;
    TextView txtTieuDeBinhLuan,txtNoiDungBinhLuan,txtSoDiem;
    RecyclerView recyclerViewHinhBinhLuan;
    List<Bitmap> bitmapList;
    BinhLuanModel binhLuanModel;
    TextView txtTieuDeToolBar;
    Toolbar toolbar;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hienthi_chitiet_binhluan);

        circleImageView = findViewById(R.id.cicleImageUser);
        txtTieuDeBinhLuan = findViewById(R.id.txtTieudebinhluan);
        txtNoiDungBinhLuan = findViewById(R.id.txtNoidungbinhluan);
        txtSoDiem = findViewById(R.id.txtDiembinhluan);
        recyclerViewHinhBinhLuan = findViewById(R.id.recyclerHinhAnhBinhLuan);

        bitmapList = new ArrayList<>();

        binhLuanModel = getIntent().getParcelableExtra("binhluanmodel");


        txtTieuDeBinhLuan.setText(binhLuanModel.getTieude());
        txtNoiDungBinhLuan.setText(binhLuanModel.getNoidung());
        txtSoDiem.setText(binhLuanModel.getChamdiem() + "");
        //   setHinhAnhBinhLuan(circleImageView,binhLuanModel.getThanhVienModel().getHinhanh());

        for (String linkhinh : binhLuanModel.getHinhanhbinhluan()) {
            StorageReference storageHinhUser = FirebaseStorage.getInstance().getReference().child("hinhanh").child(linkhinh);
            long ONE_MEGABYTE = 1024 * 1024;
            storageHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    bitmapList.add(bitmap);
                    if (bitmapList.size() == binhLuanModel.getHinhanhbinhluan().size()) {
                        AdapterRecyclerHinhBinhLuan adapterRecyclerHinhBinhLuan = new AdapterRecyclerHinhBinhLuan(HienThiChiTietBinhLuanActivity.this, R.layout.custom_layout_hinhbinhluan, bitmapList, true, binhLuanModel);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(HienThiChiTietBinhLuanActivity.this, 2); // truyền màn hình hiển thị và số cột hiện thi ảnh là 2
                        recyclerViewHinhBinhLuan.setLayoutManager(layoutManager);
                        recyclerViewHinhBinhLuan.setAdapter(adapterRecyclerHinhBinhLuan);
                        adapterRecyclerHinhBinhLuan.notifyDataSetChanged();
                    }
                }
            });
        }
        txtTieuDeToolBar=findViewById(R.id.txtTieuDeToolBar);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);

        // hiển thị nút bên trái toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }



    // ham này thực thi khi nhấn vào nút quay trở lại phía trên tool bar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /*private void setHinhAnhBinhLuan(final CircleImageView circleImageView, String linkhinh){
        StorageReference storageHinhUser = FirebaseStorage.getInstance().getReference().child("thanhvien").child(linkhinh);
        long ONE_MEGABYTE = 1024 * 1024;
        storageHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                circleImageView.setImageBitmap(bitmap);
            }
        });
    }*/
}
