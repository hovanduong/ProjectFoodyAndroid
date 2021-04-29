package com.example.foody2.View.Fragments;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foody2.Controller.OdauController;
import com.example.foody2.R;
import com.example.foody2.View.DangNhapActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    TextView txtMailProfile;
    Button btnLogout;
    FirebaseUser user;
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_fragment_profileuser,container,false);
        user=FirebaseAuth.getInstance().getCurrentUser();
        String Email=user.getEmail();
        txtMailProfile=view.findViewById(R.id.txtMailProfile);
        txtMailProfile.setText(Email);
        btnLogout=view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        if(user != null){
            Intent idLogin=new Intent(getContext(), DangNhapActivity.class);
            startActivity(idLogin);
            firebaseAuth.signOut();
            getActivity().finish();

        }
    }
}
