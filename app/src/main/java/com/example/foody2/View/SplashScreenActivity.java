package com.example.foody2.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;

import android.widget.TextView;

import com.example.foody2.R;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;


public class SplashScreenActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    TextView txtPhienBan;
    GoogleApiClient googleApiClient;
    public static final int REQUEST_PERMISSION_LOCATION = 1;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splashscreen);

        txtPhienBan = findViewById(R.id.txtPhienBan);
            sharedPreferences=getSharedPreferences("toado",MODE_PRIVATE);
        googleApiClient=new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        int checkPrermissionCoareLocation= ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int checkPrermissionFineLocation= ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(checkPrermissionCoareLocation != PackageManager.PERMISSION_GRANTED && checkPrermissionFineLocation != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_PERMISSION_LOCATION);

        }else{
            googleApiClient.connect();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_PERMISSION_LOCATION:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    googleApiClient.connect();
                }
                break;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        googleApiClient.disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Location vitrihientai=LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if(vitrihientai!=null){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("latitude", String.valueOf(vitrihientai.getLatitude()));
            editor.putString("longitude", String.valueOf(vitrihientai.getLongitude()));
            editor.commit();
        }
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            txtPhienBan.setText(getString(R.string.phienban) + "  " + packageInfo.versionName);
            Handler handler = new Handler();
            handler.postDelayed(    new Runnable() {
                @Override
                public void run() {
                    Intent iDangNhap = new Intent(SplashScreenActivity.this, DangNhapActivity.class);
                    startActivity(iDangNhap);
                    finish();
                }
            }, 2000);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
        }

