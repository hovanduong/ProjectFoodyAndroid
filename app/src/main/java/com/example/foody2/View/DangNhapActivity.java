package com.example.foody2.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.foody2.R;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class DangNhapActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, FirebaseAuth.AuthStateListener {

    private FirebaseAuth firebaseAuth;
    private TextView txtDangKyMoi, txtQuenMatKhau;
    private Button btnDangNhap;

    private EditText edEmail, edPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();


        txtDangKyMoi = findViewById(R.id.txtDangKyKhoiPhuc);

        txtQuenMatKhau = findViewById(R.id.txtQuenMatKhau);

        btnDangNhap = findViewById(R.id.btnGuiMailKhoiPhuc);
        edEmail = findViewById(R.id.edEmailKhoiPhucMatKhau);
        edPassWord = findViewById(R.id.edPassWordDangNhap);

        txtQuenMatKhau.setOnClickListener(this);
        txtDangKyMoi.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);


    }


    //end Khởi tạo client đăng nhập goole

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(this);
    }

    //End Mở form đăng nhập bằng goole


    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // lăng nghe sự kiện user  click vào button đăng nhập google,fb email account
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.txtDangKyKhoiPhuc:
                Intent idDangKy = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(idDangKy);
                break;
            case R.id.btnGuiMailKhoiPhuc:
                DangNhap();
                break;
            case R.id.txtQuenMatKhau:
                Intent idKhoiPhucMatKhau = new Intent(DangNhapActivity.this, QuenMatKhauActivity.class);
                startActivity(idKhoiPhucMatKhau);
                break;
        }
    }

    private void DangNhap() {
        String email = edEmail.getText().toString();
        String password = edPassWord.getText().toString();
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui long nhap thong tin day du", Toast.LENGTH_LONG).show();
        } else {
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
        FirebaseUser user = firebaseAuth.getCurrentUser();
        // Log.d("kiemtra",user + "");
        if (user != null) {
            String email = user.getEmail();
            if (email.equals("admin@gmail.com")) {
                Intent idThemquanan = new Intent(DangNhapActivity.this, ThemQuanAnActivity.class);
                startActivity(idThemquanan);

            } else {
                Intent idTrangChu = new Intent(DangNhapActivity.this, TrangChuActivity.class);
                startActivity(idTrangChu);
            }

        } else {

        }
    }
    // End
}
