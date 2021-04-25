package com.example.foody2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foody2.Model.ThanhVienModel;
import com.example.foody2.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    public List<ThanhVienModel> mListUser;
    public ISendUser callback;

    public ChatAdapter(ISendUser callback) {
        this.callback = callback;
    }

    public ChatAdapter(ISendUser callback, List<ThanhVienModel> mListUser) {
        this.callback = callback;
        this.mListUser = mListUser;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChatViewHolder holder, int position) {
        final ThanhVienModel user = mListUser.get(position);
        if (user == null) {
            return;
        }
        holder.tvUserName.setText(user.getHoten());
        holder.imgProfileUser.setImageResource(R.mipmap.ic_launcher);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSuccess(user.getMathanhvien());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListUser != null) {
            return mListUser.size();
        }
        return 0;
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        public TextView tvUserName;
        public CircleImageView imgProfileUser;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tv_username);
            imgProfileUser = itemView.findViewById(R.id.img_user);
        }
    }

    public interface ISendUser {
        void onSuccess(String uID);
    }

}
