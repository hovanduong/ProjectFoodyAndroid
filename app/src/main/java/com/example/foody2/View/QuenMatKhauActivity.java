package com.example.foody2.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foody2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class QuenMatKhauActivity extends AppCompatActivity implements View.OnClickListener {
        TextView txtDangKykhoiPhuc;
        Button btnGuiMailKhoiPhuc;
        EditText edEmailKhoiPhucMatKhau;
        FirebaseAuth firebaseAuth;
        ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);
        firebaseAuth=FirebaseAuth.getInstance();
        txtDangKykhoiPhuc=findViewById(R.id.txtDangKyKhoiPhuc);
        btnGuiMailKhoiPhuc=findViewById(R.id.btnGuiMailKhoiPhuc);
        edEmailKhoiPhucMatKhau=findViewById(R.id.edEmailKhoiPhucMatKhau);

        btnGuiMailKhoiPhuc.setOnClickListener(this);
        txtDangKykhoiPhuc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            int id=v.getId();
        switch (id){
            case R.id.btnGuiMailKhoiPhuc:
                String email=edEmailKhoiPhucMatKhau.getText().toString();
                boolean kiemtraemail=KiemTraEmail(email);
                if(kiemtraemail){
//                        progressDialog.setMessage(getString(R.string.dangxuly));
//                        progressDialog.setIndeterminate(true);
//                        progressDialog.show();
                        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                  //  progressDialog.dismiss();
                                    Toast.makeText(QuenMatKhauActivity.this,getString(R.string.thongbaoguimailthanhcong),Toast.LENGTH_SHORT).show();
                                }else{
                                  //  progressDialog.dismiss();
                                    Toast.makeText(QuenMatKhauActivity.this,"that bai",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                }else{
                    Toast.makeText(QuenMatKhauActivity.this,getString(R.string.thongbaoemailkhonghople),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.txtDangKyKhoiPhuc:
                Intent idDangKy=new Intent(QuenMatKhauActivity.this,DangKyActivity.class);
                startActivity(idDangKy);
                break;
        }
    }
    private boolean KiemTraEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
