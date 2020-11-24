package com.example.foody2.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;


import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.foody2.Adapter.AdapterBinhLuan;
import com.example.foody2.Model.QuanAnModel;
import com.example.foody2.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChiTietQuanAnActivity extends AppCompatActivity  implements OnMapReadyCallback {
    TextView txtTieuDeToolBar, txtTenQuanAn,txtDiaChi,txtThoiGianHoatDong,txtTrangThaiHoatDong,txtTongSoHinhAnh,txtTongSoBinhLuan,txtTongSoCheckIn,txtTongSoluuLai;
    ImageView imgHinhQuanAn;
    QuanAnModel quanAnModel;
    Toolbar toolbar;
    RecyclerView recyclerViewBinhLuan;
    AdapterBinhLuan adapterBinhLuan;
    GoogleMap googleMap;
    //MapFragment mapFragment;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_chitiet);

        quanAnModel= getIntent().getParcelableExtra("quanan");

        txtTenQuanAn=findViewById(R.id.txtTenQuanAn);
        txtDiaChi=findViewById(R.id.txtDiaChiQuanAn);
        txtThoiGianHoatDong=findViewById(R.id.txtThoiGianHoatDong);
        txtTrangThaiHoatDong=findViewById(R.id.txtTrangThaiHoatDong);
        txtTongSoHinhAnh=findViewById(R.id.tongSoHinhAnh);
        txtTongSoBinhLuan=findViewById(R.id.tongSoBinhLuan);
        txtTongSoCheckIn=findViewById(R.id.tongSocheckin);
        txtTongSoluuLai=findViewById(R.id.tongSoLuuLai);
        imgHinhQuanAn=findViewById(R.id.imageHinhQuanAn);

        // goi id để xứ lý bit map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);;
        mapFragment.getMapAsync(this);

        recyclerViewBinhLuan=findViewById(R.id.recyclerBinhLuanChiTietQuanAn);

        // xử lý tool bar
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

    @Override
    protected void onStart() {
        super.onStart();
        // lấy thời gian hiện tại
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm");
        String gioHienTai=dateFormat.format(calendar.getTime());
        String giomocua=quanAnModel.getGiomocua();
        String giodongcua=quanAnModel.getGiodongcua();

        try {
                Date dateHienTai=dateFormat.parse(gioHienTai);
                Date dateMoCua=dateFormat.parse(giomocua);
                Date dateDongCua=dateFormat.parse(giodongcua);
                if(dateHienTai.after(dateMoCua) && dateHienTai.before(dateDongCua)){
                    // gio mo cua
                    txtTrangThaiHoatDong.setText(getString(R.string.dangmocua));
                }else{
                //gio dong cua
                txtTrangThaiHoatDong.setText(getString(R.string.dadongcua));
            }
        }catch (ParseException e){
            e.printStackTrace();
        }


        txtTieuDeToolBar.setText(quanAnModel.getTenquanan());

        txtTenQuanAn.setText(quanAnModel.getTenquanan());
        txtDiaChi.setText(quanAnModel.getChiNhanhQuanAnModelList().get(0).getDiachi());
        txtThoiGianHoatDong.setText(quanAnModel.getGiomocua()+" - "+quanAnModel.getGiodongcua());
        txtTongSoHinhAnh.setText(quanAnModel.getHinhanhquanan().size() + " ");
        txtTongSoBinhLuan.setText(quanAnModel.getBinhluanModeList().size() + " ");
        txtThoiGianHoatDong.setText(giomocua +" - "+giodongcua);

        // lưu trữ hình ảnh
        StorageReference storageHinhQuanAn= FirebaseStorage.getInstance().getReference().child("hinhanh").child(quanAnModel.getHinhanhquanan().get(0));
        final long ONE_MEGABYTE=1024 * 1024;
        storageHinhQuanAn.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                imgHinhQuanAn.setImageBitmap(bitmap);
            }
        });
        // load danh sách bình luận của quán ăn
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerViewBinhLuan.setLayoutManager(layoutManager);
        adapterBinhLuan=new AdapterBinhLuan(this,R.layout.custom_layout_binhluan ,quanAnModel.getBinhluanModeList());
        recyclerViewBinhLuan.setAdapter(adapterBinhLuan);
        adapterBinhLuan.notifyDataSetChanged();



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
    this.googleMap=googleMap;


        double latitude=quanAnModel.getChiNhanhQuanAnModelList().get(0).getLatitude();
        double longtitude=quanAnModel.getChiNhanhQuanAnModelList().get(0).getLongitude();
    // lấy được kinh độ và vĩ độ rồi gán vào Latlng
        LatLng latLng=new LatLng(latitude,longtitude);
        MarkerOptions markerOptions=new MarkerOptions();
        markerOptions.position(latLng );
        markerOptions.title(quanAnModel.getTenquanan());

        googleMap.addMarker(markerOptions);

        // zum xuống đúng vị trí
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,14);
        googleMap.moveCamera(cameraUpdate);
    }
}
