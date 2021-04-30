package com.example.foody2.firebase;

import androidx.annotation.NonNull;

import com.example.foody2.Model.Message;
import com.example.foody2.Model.ThanhVienModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MessageFirebase {

    // hien thi tin nhan trong message
    public static void readMessage(final String myId, final String userId, final IReadMessage callback) {
        final List<Message> mListMessage = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mListMessage.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Message message = dataSnapshot.getValue(Message.class);
                    assert message != null;
//                    if (message.getReceiver().equals(myId) && message.getSender().equals(userId) ||
//                            message.getReceiver().equals(userId) && message.getSender().equals(myId)) {
                        mListMessage.add(message);
//                    }
                    if (!mListMessage.isEmpty()) {
                        callback.success(mListMessage);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void displayMess(String idSend, final ISendMessage callback) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(idSend);
//        đọc tin toàn bộ tin nhắn user từ firebase xuống
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//        đọc tin toàn bộ tin nhắn user
                ThanhVienModel user = snapshot.getValue(ThanhVienModel.class);
//                assert user != null;
                callback.success("Message");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    // Gửi toàn bộ thông tin tin nhắn lên firebase
    public static void sendMessage(String sender, String receiver, String messages) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Message message = new Message(sender, receiver, messages);
        reference.child("Chats").push().setValue(message);
    }

    public interface IReadMessage {
        void success(List<Message> messages);
    }

    public interface ISendMessage {
        void success(String users);
    }
}
