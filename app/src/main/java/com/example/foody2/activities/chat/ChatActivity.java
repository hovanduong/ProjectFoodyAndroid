package com.example.foody2.activities.chat;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import com.example.foody2.Adapter.ChatAdapter;
import com.example.foody2.Model.ThanhVienModel;
import com.example.foody2.R;
import com.example.foody2.activities.message.MessageActivity;

import java.util.List;

public class ChatActivity extends AppCompatActivity implements ChatAdapter.ISendUser {
    RecyclerView rcvChat;
    ChatAdapter chatAdapter;
    ChatViewModel chatViewModel = new ChatViewModel(getApplication());
    Toolbar toolbar;
    TextView txtTieuDeToolBar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        txtTieuDeToolBar = findViewById(R.id.txtTieuDeToolBar);
        txtTieuDeToolBar.setText("Chat");
        txtTieuDeToolBar = findViewById(R.id.txtTieuDeToolBar);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        init();
        // display user
        chatViewModel.displayChat(new ChatViewModel.IDisplaySuccess() {
            @Override
            public void success(List<ThanhVienModel> users) {
                chatAdapter = new ChatAdapter(ChatActivity.this, users);
                if (!users.isEmpty()){
                    rcvChat.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
                    rcvChat.setAdapter(chatAdapter);
                }else{
                    Toast.makeText(ChatActivity.this, "Data is null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // ham này thực thi khi nhấn vào nút quay trở lại phía trên tool bar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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

   
}