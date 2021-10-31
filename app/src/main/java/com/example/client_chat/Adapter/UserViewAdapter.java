package com.example.client_chat.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client_chat.Model.User;
import com.example.client_chat.R;
import com.flaviofaria.kenburnsview.KenBurnsView;

import java.util.ArrayList;

public class UserViewAdapter extends RecyclerView.Adapter<UserViewAdapter.UserViewHolder> {
    public UserViewAdapter(ArrayList<User> users) {
        this.users = users;
    }

    private final ArrayList<User> users;

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.setUserData(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        private KenBurnsView kbvUser;
        private TextView textName,bd1;
        private ImageView imgGender;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            kbvUser = itemView.findViewById(R.id.kbvUser);
            textName = itemView.findViewById(R.id.textName);
            imgGender = itemView.findViewById(R.id.imgGender);
            bd1 = itemView.findViewById(R.id.bd1);
        }

        void setUserData(User user) {
            Bitmap info = getBitmapFromString(user.getImgProfile());
            kbvUser.setImageBitmap(info);
            textName.setText(user.getUsername());
            bd1.setText(user.getBirthday());
            if (user.getGender() == 1) {
                imgGender.setImageResource(R.drawable.man_day);
            }
        }
    }

    private static Bitmap getBitmapFromString(String image) {
        byte[] bytes = Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
