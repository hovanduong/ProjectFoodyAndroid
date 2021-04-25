package com.example.foody2.activities.message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foody2.Adapter.MessageAdapter;
import com.example.foody2.R;
import com.google.firebase.auth.FirebaseAuth;

public class MessageActivity extends AppCompatActivity {
    MessageAdapter messageAdapter;
    EditText edtSend;
    TextView tvUserName;
    ImageButton imgSend;
    RecyclerView rcvMessage;
    ImageView imgBack;
    MessageViewModel messageViewModel = new MessageViewModel(getApplication());
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        init();
        fetchDataMessage();
        readMess();
        userId = getIntent().getExtras().getString("UID");

        //send mesage
        imgSend.setOnClickListener(v -> {
            String msg = edtSend.getText().toString().trim();
            if (!msg.equals("")) {
                messageViewModel.sendMess(FirebaseAuth.getInstance().getUid(), userId, msg);
            } else {
                Toast.makeText(MessageActivity.this, "You can't send message", Toast.LENGTH_SHORT).show();
            }
            edtSend.setText("");
        });

        imgBack.setOnClickListener(view -> finish());
    }

    @SuppressLint("SetTextI18n")
    private void readMess() {
        messageViewModel.displayMess(FirebaseAuth.getInstance().getUid(), userName -> {
            tvUserName.setText("Message");
            messageViewModel.getMessage(FirebaseAuth.getInstance().getUid(), userId, messages -> {
                messageAdapter = new MessageAdapter(getApplicationContext(), messages);
                rcvMessage.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rcvMessage.scrollToPosition(messages.size() - 1);
                rcvMessage.setAdapter(messageAdapter);
            });
        });
    }

    private void fetchDataMessage() {
    }

    private void init() {
        tvUserName = findViewById(R.id.tv_username_message);
        edtSend = findViewById(R.id.edt_send);
        imgSend = findViewById(R.id.btn_send);
        imgBack = findViewById(R.id.img_backUser);

        rcvMessage = findViewById(R.id.rcv_message);
        rcvMessage.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcvMessage.setLayoutManager(linearLayoutManager);
    }
}