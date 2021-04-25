package com.example.foody2.activities.message;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.foody2.Model.Message;
import com.example.foody2.firebase.MessageFirebase;

import java.util.List;

public class MessageViewModel extends AndroidViewModel {
    public MessageViewModel(@NonNull Application application) {
        super(application);
    }
    public void getMessage(String send, String recei, final IReadMess callback){
        MessageFirebase.readMessage(send, recei, new MessageFirebase.IReadMessage() {
            @Override
            public void success(List<Message> messages) {
                callback.success(messages);
            }
        });
    }
    public void displayMess(String send, final IDisplaMess callback){
        MessageFirebase.displayMess(send, new MessageFirebase.ISendMessage() {
            @Override
            public void success(String users) {
                callback.success(users);
            }
        });
    }
    public void sendMess(String send, String receiver, String msg){
        MessageFirebase.sendMessage(send, receiver, msg);
    }
    public interface IReadMess{
        void success(List<Message> messages);
    }
    public interface IDisplaMess{
        void success(String userName);
    }
}
