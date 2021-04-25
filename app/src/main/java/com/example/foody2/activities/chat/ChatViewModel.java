package com.example.foody2.activities.chat;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.foody2.Model.ThanhVienModel;
import com.example.foody2.firebase.ChatFirebase;

import java.util.List;

public class ChatViewModel extends AndroidViewModel {
    public ChatViewModel(@NonNull Application application) {
        super(application);
    }
    public void displayChat(final IDisplaySuccess callback){
        ChatFirebase.readUsers(new ChatFirebase.IReadUser() {
            @Override
            public void onSuccess(List<ThanhVienModel> users) {
                callback.success(users);
            }
        });
    }

    public interface IDisplaySuccess{
        void success(List<ThanhVienModel> users);
    }
}
