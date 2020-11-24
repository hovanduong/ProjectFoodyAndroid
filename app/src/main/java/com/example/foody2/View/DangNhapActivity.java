package com.example.foody2.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.foody2.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
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


public class DangNhapActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener , View.OnClickListener,FirebaseAuth.AuthStateListener {
    SignInButton btnDangNhapGoogle;
    LoginButton btnDangNhapFaceBook;
    CallbackManager callbackManagerFaceBook;
    GoogleApiClient apiClient;
    public static int RESERQUEST_CODE_DANGNHAP_GOOLE=99;
    public static int KIEMTRA_PROVIDER_DANGNHAP=0;
    FirebaseAuth firebaseAuth;
    TextView txtDangKyMoi,txtQuenMatKhau;
    Button btnDangNhap;

    EditText edEmail,edPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_dang_nhap);
        callbackManagerFaceBook=CallbackManager.Factory.create();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        LoginManager.getInstance().logOut();

        btnDangNhapGoogle=findViewById(R.id.btnDangNhapGoogle);
        txtDangKyMoi=findViewById(R.id.txtDangKyKhoiPhuc);
        btnDangNhapFaceBook=findViewById(R.id.btnDangNhapFaceBook);
        txtQuenMatKhau=findViewById(R.id.txtQuenMatKhau);

        btnDangNhap=findViewById(R.id.btnGuiMailKhoiPhuc);
        edEmail=findViewById(R.id.edEmailKhoiPhucMatKhau);
        edPassWord=findViewById(R.id.edPassWordDangNhap);

        txtQuenMatKhau.setOnClickListener(this);
        txtDangKyMoi.setOnClickListener(this );
        btnDangNhap.setOnClickListener(this);

        btnDangNhapFaceBook.setReadPermissions("email","public_profile");
        btnDangNhapFaceBook.registerCallback(callbackManagerFaceBook, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                KIEMTRA_PROVIDER_DANGNHAP=2;
                String tokenId=loginResult.getAccessToken().getToken();
                ChungThucDangNhapFireBase(tokenId);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        btnDangNhapGoogle.setOnClickListener(this);
        TaoClientDangNhapGooogle();
    }
    // Khởi tạo client đăng nhập goole
    private  void TaoClientDangNhapGooogle(){
        // Configure Google Sign In
        // Configure Google Sign In
       GoogleSignInOptions signInOptions=new GoogleSignInOptions.Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
             apiClient=new GoogleApiClient.Builder(this)
                    .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions)
                .build();

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

    // Mở form đăng nhập bằng goole
    private void DangNhapGoole(GoogleApiClient apiClient){
        KIEMTRA_PROVIDER_DANGNHAP=1;
        Intent  IdGoogle=Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(IdGoogle,RESERQUEST_CODE_DANGNHAP_GOOLE);

    }
    //End Mở form đăng nhập bằng goole

    //Lay tokenId đã đăng nhập google để đăng nhập trên firebase
    private  void ChungThucDangNhapFireBase(String token) {

        if (KIEMTRA_PROVIDER_DANGNHAP == 1) {
           AuthCredential  authCredential = GoogleAuthProvider.getCredential(token, null);
            firebaseAuth.signInWithCredential(authCredential);
        }else if (KIEMTRA_PROVIDER_DANGNHAP==2){
           AuthCredential  authCredential1= FacebookAuthProvider.getCredential(token);
            firebaseAuth.signInWithCredential(authCredential1);
        }

    }
    //End Lay tokenId đã đăng nhập google để đăng nhập trên firebase
    @Override

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESERQUEST_CODE_DANGNHAP_GOOLE){
            if(resultCode==RESULT_OK){
                    GoogleSignInResult  signInResult=   Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                GoogleSignInAccount account=signInResult.getSignInAccount();
                String tokenID=account.getIdToken();
                ChungThucDangNhapFireBase(tokenID);
            }
        }else {
            callbackManagerFaceBook.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

// lăng nghe sự kiện user  click vào button đăng nhập google,fb email account
    @Override
    public void onClick(View v) {
            int id=v.getId();
            switch (id){
                case R.id.btnDangNhapGoogle:
                    DangNhapGoole(apiClient);
                    break;
                case R.id.txtDangKyKhoiPhuc:
                    Intent idDangKy= new Intent(DangNhapActivity.this, DangKyActivity.class);
                    startActivity(idDangKy);
                    break;
                case R.id.btnGuiMailKhoiPhuc:
                    DangNhap();
                    break;
                case R.id.txtQuenMatKhau:
                    Intent idKhoiPhucMatKhau= new Intent(DangNhapActivity.this, QuenMatKhauActivity.class);
                    startActivity(idKhoiPhucMatKhau);
                    break;
            }
    }
    private  void DangNhap(){
            String email=edEmail.getText().toString();
            String password=edPassWord.getText().toString();
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(DangNhapActivity.this,getString(R.string.dangnhapthatbai),Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }
//end  lăng nghe sự kiện user  click vào button đăng nhập google,fb email account

    // Sự Kiện kiểm tra người dùng đã đăng nhập thành công hay đăng xuất
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user!=null){
            Intent idTrangChu=new Intent(DangNhapActivity.this,TrangChuActivity.class);
            startActivity(idTrangChu);
        }else{

        }
    }
    // End
}
