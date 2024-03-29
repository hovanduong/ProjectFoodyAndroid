package com.example.foody2.View;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foody2.R;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.Arrays;
import java.util.List;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


public class DangNhapActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, FirebaseAuth.AuthStateListener {
    Button btnDangNhapGoogle;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private TextView txtDangKyMoi, txtQuenMatKhau;
    LinearLayout btnDangNhap, btnDangNhapFB;
    private EditText edEmail, edPassWord;
    CallbackManager callbackManager;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        FacebookSdk.sdkInitialize(getApplicationContext());

        firebaseAuth = FirebaseAuth.getInstance();
        //firebaseAuth.signOut();

        callbackManager = CallbackManager.Factory.create();
        txtDangKyMoi = findViewById(R.id.txtDangKyKhoiPhuc);

        txtQuenMatKhau = findViewById(R.id.txtQuenMatKhau);

        btnDangNhap = findViewById(R.id.btnDangnhap);
        edEmail = findViewById(R.id.edEmailKhoiPhucMatKhau);
        edPassWord = findViewById(R.id.edPassWordDangNhap);
        btnDangNhapFB = findViewById(R.id.btn_Login_FB);

        progressDialog = new ProgressDialog(this);
        txtQuenMatKhau.setOnClickListener(this);
        txtDangKyMoi.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);
        btnDangNhapFB.setOnClickListener(this);
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    progressDialog.dismiss();
                    Intent intent = new Intent(DangNhapActivity.this, TrangChuActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        };
    }


    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(firebaseAuthListener);
    }


    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_Login_FB:
                DanhNhapFb();

                break;
            case R.id.txtDangKyKhoiPhuc:
                Intent idDangKy = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(idDangKy);
                break;
            case R.id.btnDangnhap:
                DangNhap();
                break;
            case R.id.txtQuenMatKhau:
                Intent idKhoiPhucMatKhau = new Intent(DangNhapActivity.this, QuenMatKhauActivity.class);
                startActivity(idKhoiPhucMatKhau);
                break;
        }
    }

    private void DanhNhapFb() {
        LoginManager.getInstance().logInWithReadPermissions(DangNhapActivity.this, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("kiemtra", "111");
                handleFacebookAccessToken(loginResult.getAccessToken().getToken());
                Log.d("kiemtra", "111111");
//                firebaseAuthListener.onAuthStateChanged(firebaseAuth);

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.d("facebook:onError", "facebook:onError", error);
            }
        });
    }

    private void DangNhap() {
        String email = edEmail.getText().toString().trim();
        String password = edPassWord.getText().toString().trim();
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui long nhap thong tin day du", Toast.LENGTH_LONG).show();
        } else {
            progressDialog.setMessage(getString(R.string.dangxuly));
            progressDialog.setIndeterminate(true);
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(DangNhapActivity.this, getString(R.string.dangnhapthatbai), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }
//end  lăng nghe sự kiện user  click vào button đăng nhập google,fb email account

    // Sự Kiện kiểm tra người dùng đã đăng nhập thành công hay đăng xuất
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        progressDialog.dismiss();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        // Log.d("kiemtra",user + "");
        if (user != null) {
            String email = user.getEmail().trim();
//            String email=edEmail.getText().toString().trim();
//            String password=edPassWord.getText().toString().trim();
            if (email.equals("admin@gmail.com")) {
                Intent idThemquanan = new Intent(DangNhapActivity.this, ThemQuanAnActivity.class);
                startActivity(idThemquanan);


            } else {

                Intent idTrangChu = new Intent(DangNhapActivity.this, TrangChuActivity.class);
                startActivity(idTrangChu);
            }

        }
    }

    // End
    private void handleFacebookAccessToken(String token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token);
        firebaseAuth.signInWithCredential(credential);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}


