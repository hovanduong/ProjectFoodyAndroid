package com.example.foody2.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foody2.Controller.DangKyController;
import com.example.foody2.Model.ThanhVienModel;
import com.example.foody2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnDangKy;
    EditText edEmailDK,edPassWorDk,edNhapLaiPassWord;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    DangKyController dangKyController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        btnDangKy=findViewById(R.id.btnGuiMailKhoiPhuc);
        edEmailDK=findViewById(R.id.edEmailKhoiPhucMatKhau);
        edPassWorDk=findViewById(R.id.edPassWordDangNhap);
        edNhapLaiPassWord=findViewById(R.id.edNhapLaiPassWordDK);
        btnDangKy.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        progressDialog.setMessage(getString(R.string.dangxuly));
        progressDialog.setIndeterminate(true);
        progressDialog.show();
            final String email=edEmailDK.getText().toString();
             String matkhau=edPassWorDk.getText().toString();
            String nhaplaimatkhau=edNhapLaiPassWord.getText().toString();
            String thongbaoloi=getString(R.string.thongbaoloidangky);
            if(email.isEmpty()){
                thongbaoloi+=getString(R.string.Email);
                Toast.makeText(DangKyActivity.this,thongbaoloi,Toast.LENGTH_LONG).show();
            }else if(matkhau.isEmpty()){
                thongbaoloi+=getString(R.string.PasWord);
                Toast.makeText(DangKyActivity.this,thongbaoloi,Toast.LENGTH_LONG).show();
            }else if(!nhaplaimatkhau.equals( matkhau)){
                Toast.makeText(this,getString(R.string.thongbaonhaplaimatkhau),Toast.LENGTH_LONG).show();
            }else{
                firebaseAuth.createUserWithEmailAndPassword(email,matkhau).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            ThanhVienModel thanhVienModel=new ThanhVienModel();
                            thanhVienModel.setHoten(email);
                            thanhVienModel.setHinhanh("user.png");
                            String uid= task.getResult().getUser().getUid();
                            dangKyController=new DangKyController();
                            dangKyController.ThemThongTinThanhVien(thanhVienModel,uid);
                            Toast.makeText(DangKyActivity.this,getString(R.string.dangkythanhcong),Toast.LENGTH_LONG).show();
                            Intent idDangNhap=new Intent(DangKyActivity.this,DangNhapActivity.class);
                            startActivity(idDangNhap);
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(DangKyActivity.this,"that bai ",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
    }
}
