package com.example.foody2.activities.like;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.foody2.firebase.LikeFirebase;

public class LikeViewModel extends AndroidViewModel {
    public LikeViewModel(@NonNull Application application) {
        super(application);
    }
    public void displayLike( int count){
        LikeFirebase.sendData(count);
    }
    public void changeLike(int count, ILikeCount callback){
        LikeFirebase.changeLike(count, new LikeFirebase.ICallback() {
            @Override
            public void onSuccess(int count) {
                callback.onSuccess(count);
            }
        });
    }
    public interface ILikeCount{
        void onSuccess(int count);
    }
}
