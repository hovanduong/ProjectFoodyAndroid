package com.example.foody2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Model.Message;

import com.example.foody2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageAdapterViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;
    public Context mContext;
    private List<Message> mListMessage;
    FirebaseUser firebaseUser;

    public MessageAdapter(Context mContext, List<Message> mListMessage) {
        this.mContext = mContext;
        this.mListMessage = mListMessage;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageAdapter.MessageAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_chat_right, parent, false);
            return new MessageAdapterViewHolder(view);
        }else{
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_chat_left, parent, false);
            return new MessageAdapterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MessageAdapterViewHolder holder, int position) {
        Message message = mListMessage.get(position);
        //set tin nhan
        holder.tvMessage.setText(message.getMessage());
    }

    @Override
    public int getItemCount() {
        if (mListMessage != null){
            return mListMessage.size();
        }
        return 0;
    }

    public class MessageAdapterViewHolder extends RecyclerView.ViewHolder{
        public TextView tvMessage;
        public ImageView imgMessage;
        public MessageAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tv_message);
            imgMessage = itemView.findViewById(R.id.img_profile_chat);
        }
    }

    @Override
    public int getItemViewType(int position) {
        //người gửi là bên phải và người nhận là bên trái
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mListMessage.get(position).getSender().equals(firebaseUser.getUid())){
            return MSG_TYPE_RIGHT;
        }else{
            return MSG_TYPE_LEFT;
        }
    }
}

