package com.example.client_chat.Adapter;

import static com.example.client_chat.Activity.MainActivity.Username;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.client_chat.Interface.ItemClickListener;
import com.example.client_chat.Model.Messenger;
import com.example.client_chat.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.OutputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import me.jagar.chatvoiceplayerlibrary.VoicePlayerView;


public class ContentImageAdapter extends RecyclerView.Adapter<ContentImageAdapter.ViewHolder> {
    Bitmap bitmap;
    private final ArrayList<Messenger> messageFormats;
    private final Context context;
    Dialog dialog;
    LottieAnimationView smile, wow, cry, angry;
    AlertDialog.Builder a;
    AlertDialog b;
    OutputStream outputStream;
    CircleImageView logo;
    TextView NameInfo;
    ImageView nam_nu, bg_user;
    FloatingActionButton SendMess, CallVideo;
    public static String fileN = null;
    int switchNumber = 0;

    public ContentImageAdapter(ArrayList<Messenger> messageFormats, Context context) {
        this.messageFormats = messageFormats;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_image, parent, false);
        dialog = new Dialog(context);
        return new ContentImageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Messenger message = messageFormats.get(position);
        holder.avatar.setVisibility(View.GONE);
        holder.theirImage.setVisibility(View.GONE);
        holder.myImage.setVisibility(View.GONE);
        holder.myMessage.setVisibility(View.GONE);
        holder.theirMessage.setVisibility(View.GONE);
        holder.myVoicePlayerView.setVisibility(View.GONE);
        holder.theirVoicePlayerView.setVisibility(View.GONE);
        if (message.getUsername().equals(Username)) {
            if (message.getType() == 1) {
                holder.myMessage.setVisibility(View.VISIBLE);
                holder.myMessage.setText(message.getMessage());
            } else if (message.getType() == 2) {
                holder.myImage.setVisibility(View.VISIBLE);
                Bitmap bitmap = getBitmapFromString(message.getMessage());
                holder.myImage.setImageBitmap(bitmap);
            } else if (message.getType() == 3) {
                holder.myVoicePlayerView.setVisibility(View.VISIBLE);
                String url = "data:audio/3gp;base64," + message.getMessage();
                holder.myVoicePlayerView.setAudio(url);
//                String strippedAudioData = message.getMessage().substring(message.getMessage().indexOf(',') + 1);
//                byte[] data = Base64.decode(strippedAudioData, Base64.DEFAULT);
//                AudioTrack audioTrack = new AudioTrack(
//                        AudioManager.STREAM_MUSIC, 44100,
//                        AudioFormat.CHANNEL_CONFIGURATION_MONO,
//                        AudioFormat.ENCODING_PCM_16BIT,
//                        data.length,
//                        AudioTrack.MODE_STATIC);
//                audioTrack.write(data, 0, data.length);
//                audioTrack.play();
//                audioTrack.release();
            }
        } else if (!message.getUsername().equals(Username)) {
            holder.myImage.setVisibility(View.GONE);
            holder.myMessage.setVisibility(View.GONE);
            holder.avatar.setVisibility(View.VISIBLE);
            holder.theirImage.setVisibility(View.GONE);
            holder.theirMessage.setVisibility(View.GONE);
            holder.theirVoicePlayerView.setVisibility(View.GONE);
            Bitmap info = getBitmapFromString(message.getImgProfile());
            holder.avatar.setImageBitmap(info);
            if (message.getType() == 2) {
                holder.theirImage.setVisibility(View.VISIBLE);
                Bitmap bitmap = getBitmapFromString(message.getMessage());
                holder.theirImage.setImageBitmap(bitmap);
            } else if (message.getType() == 1) {
                holder.theirMessage.setVisibility(View.VISIBLE);
                holder.theirMessage.setText(message.getMessage());
            } else if (message.getType() == 3) {
                holder.theirVoicePlayerView.setVisibility(View.VISIBLE);
                String url = "data:audio/3gp;base64," + message.getMessage();

                holder.theirVoicePlayerView.setAudio(url);
            }
        }
        holder.setItemClickListener((v, position1) -> {
            holder.avatar.setOnClickListener(view -> {
                AlertDialog.Builder a = new AlertDialog.Builder(context);
                View vv = LayoutInflater.from(context).inflate(R.layout.information_user, null);
                a.setView(vv);
                AlertDialog b = a.create();
                b.getWindow().setGravity(Gravity.CENTER);
                b.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                b.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                b.show();
                bg_user = b.findViewById(R.id.bg_user);
                logo = b.findViewById(R.id.logo);
                NameInfo = b.findViewById(R.id.NameInfo);
                nam_nu = b.findViewById(R.id.nam_nu);
                SendMess = b.findViewById(R.id.SendMess);
                CallVideo = b.findViewById(R.id.CallVideo);
                Bitmap info1 = getBitmapFromString(message.getImgProfile());
                logo.setImageBitmap(info1);
                bg_user.setImageBitmap(info1);
                NameInfo.setText(message.getUsername());
                if (message.getGender() == 1) {
                    nam_nu.setImageResource(R.drawable.man_day);
                }
                SendMess.setOnClickListener(view2 -> {

                });
                CallVideo.setOnClickListener(view2 -> {

                });
            });
            v.setOnLongClickListener(view -> {
                AlertDialogStatus();
                dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.bottomsheet_delete);
                LinearLayout linear_reply = dialog.findViewById(R.id.linear_reply);
                LinearLayout linear_copy = dialog.findViewById(R.id.linear_copy);
                LinearLayout linear_Delete = dialog.findViewById(R.id.linear_Delete);
                linear_reply.setOnClickListener(view1 -> {

                });
                linear_copy.setOnClickListener(view1 -> {
                    ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("TextView", message.getMessage());
                    clipboard.setPrimaryClip(clip);
                    dialog.dismiss();
                    b.dismiss();
                    Toast.makeText(context, "Copy successful", Toast.LENGTH_SHORT).show();
                });
                linear_Delete.setOnClickListener(view1 -> {

                });
                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                return true;
            });
        });
    }

    private void AlertDialogStatus() {
        a = new AlertDialog.Builder(context);
        View icon = LayoutInflater.from(context).inflate(R.layout.status_message, null);
        a.setView(icon);
        b = a.create();
        b.getWindow().setGravity(Gravity.CENTER);
        b.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        b.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        b.show();
        cry = icon.findViewById(R.id.cry);
        smile = icon.findViewById(R.id.smile);
        wow = icon.findViewById(R.id.wow);
        angry = icon.findViewById(R.id.angry);
        cry.setOnClickListener(view -> {
            b.dismiss();
        });
        smile.setOnClickListener(view -> {
            b.dismiss();
        });
        wow.setOnClickListener(view -> {
            b.dismiss();
        });
        angry.setOnClickListener(view -> {
            b.dismiss();
        });
    }

    private Bitmap getBitmapFromString(String image) {
        byte[] bytes = Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    @Override
    public int getItemCount() {
        return messageFormats.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ItemClickListener itemClickListener;
        RelativeLayout relative_layout;
        CircleImageView avatar;
        PhotoView theirImage, myImage;
        TextView theirMessage, myMessage;
        VoicePlayerView myVoicePlayerView, theirVoicePlayerView;

        public ViewHolder(@NonNull View view) {
            super(view);
            relative_layout = view.findViewById(R.id.relative_layout);
            avatar = view.findViewById(R.id.avatar);
            theirImage = view.findViewById(R.id.theirImage);
            myImage = view.findViewById(R.id.myImage);
            myMessage = view.findViewById(R.id.myMessage);
            theirMessage = view.findViewById(R.id.theirMessage);
            theirVoicePlayerView = view.findViewById(R.id.theirVoicePlayerView);
            myVoicePlayerView = view.findViewById(R.id.myVoicePlayerView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view, getAdapterPosition());
        }

        public void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener = ic;
        }
    }
}
