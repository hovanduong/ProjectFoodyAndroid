package com.example.foody2.activities.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foody2.Adapter.ChatAdapter;
import com.example.foody2.Model.ThanhVienModel;
import com.example.foody2.R;
import com.example.foody2.View.Fragments.OdauFragment;
import com.example.foody2.View.TrangChuActivity;
import com.example.foody2.activities.message.MessageActivity;

import java.util.List;

public class ChatActivity extends AppCompatActivity implements ChatAdapter.ISendUser, View.OnClickListener {
    RecyclerView rcvChat;
    ChatAdapter chatAdapter;
    ChatViewModel chatViewModel = new ChatViewModel(getApplication());
    ImageView imgBackHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        init();
        imgBackHome = findViewById(R.id.img_backchat);
        imgBackHome.setOnClickListener(this);
        // display user
        chatViewModel.displayChat(users -> {
            chatAdapter = new ChatAdapter(ChatActivity.this, users);
            if (!users.isEmpty()){
                rcvChat.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
                rcvChat.setAdapter(chatAdapter);
            }else{
                Toast.makeText(ChatActivity.this, "Data is null", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void init() {
        rcvChat = findViewById(R.id.rcv_Chat);
    }

    @Override
    public void onSuccess(String uID) {
        Intent intent = new Intent(ChatActivity.this, MessageActivity.class);
        intent.putExtra("UID", uID);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Intent idHome = new Intent(ChatActivity.this, TrangChuActivity.class);
        startActivity(idHome);
    }
}