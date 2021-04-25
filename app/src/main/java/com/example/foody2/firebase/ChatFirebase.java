package com.example.foody2.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foody2.Model.ThanhVienModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatFirebase {
    public static void readUsers(final IReadUser callback) {
        final List<ThanhVienModel> mListUser = new ArrayList<>();
//        final FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("thanhviens");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ThanhVienModel user = dataSnapshot.getValue(ThanhVienModel.class);
//                        if (!user.getMathanhvien().equals(fUser.getUid())){
                            mListUser.add(user);
//                        }
                }
                Log.d("check", "abc: " + mListUser.size());
                callback.onSuccess(mListUser);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public interface IReadUser {
        void onSuccess(List<ThanhVienModel> users);
    }
}
