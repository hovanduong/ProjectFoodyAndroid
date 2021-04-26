package com.example.foody2.firebase;

import android.preference.Preference;
import android.telecom.Call;

import com.example.foody2.Model.CountLike;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LikeFirebase {
    public static void sendData(int count){
        CountLike countLike = new CountLike(count);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("likes").push();
        reference.setValue(countLike);
    }
    public static void changeLike(int count, ICallback callback){
            callback.onSuccess(count);
    }

    public interface ICallback{
        void onSuccess(int count);
    }
}
