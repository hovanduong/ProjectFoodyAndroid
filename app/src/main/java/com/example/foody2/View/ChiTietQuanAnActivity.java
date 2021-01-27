package com.example.foody2.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.foody2.Adapter.AdapterBinhLuan;
import com.example.foody2.Controller.ChiTietQuanAnController;
import com.example.foody2.Model.QuanAnModel;
import com.example.foody2.Model.TienIchModel;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChiTietQuanAnActivity extends AppCompatActivity  implements OnMapReadyCallback {
    TextView txtTieuDeToolBar, txtTenQuanAn,txtDiaChi,txtThoiGianHoatDong,txtTrangThaiHoatDong,txtTongSoHinhAnh,
            txtTongSoBinhLuan,txtTongSoCheckIn,txtTongSoluuLai,txtGioiHanGia,txtTenWifi,txtMatKhauWifi,txtNgayDangWifi;
    ImageView imgHinhQuanAn;
    QuanAnModel quanAnModel;
    Toolbar toolbar;
    RecyclerView recyclerViewBinhLuan;
    AdapterBinhLuan adapterBinhLuan;
    GoogleMap googleMap;
    LinearLayout khungtienich,khungWifi;
    ChiTietQuanAnController chiTietQuanAnController;

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
        txtGioiHanGia=findViewById(R.id.txtGioiHanGia);
        khungtienich=findViewById(R.id.khungtienich);

        txtTenWifi=findViewById(R.id.txtTenWifi);
        txtMatKhauWifi=findViewById(R.id.txtMatKhauWifi);
        txtNgayDangWifi=findViewById(R.id.txtNgayDangWifi);
        khungWifi=findViewById(R.id.khungWifi);
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


        chiTietQuanAnController=new ChiTietQuanAnController();
        HienThiThiChiTietQUanAn();
    }
// ham này thực thi khi nhấn vào nút quay trở lại phía trên tool bar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private  void HienThiThiChiTietQUanAn(){
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
        downLoadHinhTienIich();


        txtTieuDeToolBar.setText(quanAnModel.getTenquanan());

        txtTenQuanAn.setText(quanAnModel.getTenquanan());
        txtDiaChi.setText(quanAnModel.getChiNhanhQuanAnModelList().get(0).getDiachi());
        txtThoiGianHoatDong.setText(quanAnModel.getGiomocua()+" - "+quanAnModel.getGiodongcua());
        txtTongSoHinhAnh.setText(quanAnModel.getHinhanhquanan().size() + " ");
        txtTongSoBinhLuan.setText(quanAnModel.getBinhluanModeList().size() + " ");
        txtThoiGianHoatDong.setText(giomocua +" - "+giodongcua);


        if(quanAnModel.getGiatoithieu() != 0 && quanAnModel.getGiatoida() != 0){
            NumberFormat numberFormat=new DecimalFormat("###,###"); // NumvberFormat java.text
            String giatoithieuu=numberFormat.format(quanAnModel.getGiatoithieu())+"đ";
            String giatoidaa=numberFormat.format(quanAnModel.getGiatoida())+"đ";

            txtGioiHanGia.setText(giatoithieuu + "đ"+" - " + giatoidaa+"đ");
        }else{
            txtGioiHanGia.setVisibility(View.INVISIBLE);
        }


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

        // Muốn hiển thị thằng nào thì truyền nói vào
        chiTietQuanAnController.HienThiDanhSachWifWi(quanAnModel.getMaquanan(),txtTenWifi,txtMatKhauWifi,txtNgayDangWifi);
        khungWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent idDanhSachWifi=new Intent(ChiTietQuanAnActivity.this,CapNhatDanhSachWiFiActivity.class);
                idDanhSachWifi.putExtra("maquanan",quanAnModel.getMaquanan());
                startActivity(idDanhSachWifi);
            }
        });

    }

   // @Override
//    protected void onStart() {
//        super.onStart();
//        // lấy thời gian hiện tại
//        Calendar calendar=Calendar.getInstance();
//        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm");
//        String gioHienTai=dateFormat.format(calendar.getTime());
//        String giomocua=quanAnModel.getGiomocua();
//        String giodongcua=quanAnModel.getGiodongcua();
//
//        try {
//                Date dateHienTai=dateFormat.parse(gioHienTai);
//                Date dateMoCua=dateFormat.parse(giomocua);
//                Date dateDongCua=dateFormat.parse(giodongcua);
//                if(dateHienTai.after(dateMoCua) && dateHienTai.before(dateDongCua)){
//                    // gio mo cua
//                    txtTrangThaiHoatDong.setText(getString(R.string.dangmocua));
//                }else{
//                //gio dong cua
//                txtTrangThaiHoatDong.setText(getString(R.string.dadongcua));
//            }
//        }catch (ParseException e){
//            e.printStackTrace();
//        }
//        downLoadHinhTienIich();
//
//
//        txtTieuDeToolBar.setText(quanAnModel.getTenquanan());
//
//        txtTenQuanAn.setText(quanAnModel.getTenquanan());
//        txtDiaChi.setText(quanAnModel.getChiNhanhQuanAnModelList().get(0).getDiachi());
//        txtThoiGianHoatDong.setText(quanAnModel.getGiomocua()+" - "+quanAnModel.getGiodongcua());
//        txtTongSoHinhAnh.setText(quanAnModel.getHinhanhquanan().size() + " ");
//        txtTongSoBinhLuan.setText(quanAnModel.getBinhluanModeList().size() + " ");
//        txtThoiGianHoatDong.setText(giomocua +" - "+giodongcua);
//
//
//        if(quanAnModel.getGiatoithieu() != 0 && quanAnModel.getGiatoida() != 0){
//            NumberFormat numberFormat=new DecimalFormat("###,###"); // NumvberFormat java.text
//            String giatoithieuu=numberFormat.format(quanAnModel.getGiatoithieu())+"đ";
//            String giatoidaa=numberFormat.format(quanAnModel.getGiatoida())+"đ";
//
//                txtGioiHanGia.setText(giatoithieuu + "đ"+" - " + giatoidaa+"đ");
//        }else{
//            txtGioiHanGia.setVisibility(View.INVISIBLE);
//        }
//
//
//        // lưu trữ hình ảnh
//        StorageReference storageHinhQuanAn= FirebaseStorage.getInstance().getReference().child("hinhanh").child(quanAnModel.getHinhanhquanan().get(0));
//        final long ONE_MEGABYTE=1024 * 1024;
//        storageHinhQuanAn.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//            @Override
//            public void onSuccess(byte[] bytes) {
//                Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
//                imgHinhQuanAn.setImageBitmap(bitmap);
//            }
//        });
//        // load danh sách bình luận của quán ăn
//        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
//        recyclerViewBinhLuan.setLayoutManager(layoutManager);
//        adapterBinhLuan=new AdapterBinhLuan(this,R.layout.custom_layout_binhluan ,quanAnModel.getBinhluanModeList());
//        recyclerViewBinhLuan.setAdapter(adapterBinhLuan);
//        adapterBinhLuan.notifyDataSetChanged();
//
//
//        chiTietQuanAnController.HienThiDanhSachWifWi(quanAnModel.getMaquanan(),txtTenWifi,txtMatKhauWifi,txtNgayDangWifi);
//    }

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
    private void downLoadHinhTienIich(){

        for(String matienich : quanAnModel.getTienich()) {
            DatabaseReference noteTienIch= FirebaseDatabase.getInstance().getReference().child("quanlytienichs").child(matienich);
            noteTienIch.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    TienIchModel tienIchModel=dataSnapshot.getValue(TienIchModel.class);
                    StorageReference storageHinhQuanAn = FirebaseStorage.getInstance().getReference().child("tienich").child(tienIchModel.getHinhtienich());
                    final long ONE_MEGABYTE = 1024 * 1024;
                    storageHinhQuanAn.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            // set hinh anh động
                            ImageView imgTienIich=new ImageView(ChiTietQuanAnActivity.this);
                            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(50,50);
                            layoutParams.setMargins(10,10,10,10);
                            imgTienIich.setLayoutParams(layoutParams);
                            imgTienIich.setScaleType(ImageView.ScaleType.FIT_XY);
                            imgTienIich.setPadding(5,5,5,5);


                            imgTienIich.setImageBitmap(bitmap);
                            khungtienich.addView(imgTienIich);
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }
}
