package com.example.client_chat.Adapter;

import static androidx.core.content.res.ResourcesCompat.getFont;
import static com.example.client_chat.Activity.MainActivity.uniqueId;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.client_chat.Interface.ItemClickListener;
import com.example.client_chat.Model.User;
import com.example.client_chat.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.skydoves.balloon.ArrowOrientation;
import com.skydoves.balloon.ArrowPositionRules;
import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;
import com.skydoves.balloon.BalloonSizeSpec;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import www.sanju.motiontoast.MotionToast;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private final ArrayList<User> users;
    private final Context context;
    Dialog dialog;
    CircleImageView logo;
    TextView NameInfo, tvsend, tvcall, birthday;
    ImageView nam_nu, bg_user;
    FloatingActionButton SendMess, CallVideo;
    Balloon balloon;
    ImageButton tai_hinh_anh;
    OutputStream outputStream;
    PhotoView photoView;

    public UserAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_connected, parent, false);
        dialog = new Dialog(context);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User message = users.get(position);
        Bitmap info = getBitmapFromString(message.getImgProfile());
        holder.user.setImageBitmap(info);
        holder.name.setText(message.getUsername());
        holder.setItemClickListener((v, position1) -> {
            v.setOnClickListener(view -> {
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
                tvsend = b.findViewById(R.id.tvsend);
                tvcall = b.findViewById(R.id.tvcall);
                birthday = b.findViewById(R.id.birthday);
                birthday.setText(message.getBirthday());
                Bitmap info1 = getBitmapFromString(message.getImgProfile());
                logo.setImageBitmap(info1);
                bg_user.setImageBitmap(info1);
                NameInfo.setText(message.getUsername());
                if (message.getGender() == 1) {
                    nam_nu.setImageResource(R.drawable.man_day);
                }
                if (message.getUniqueId().equals(uniqueId)) {
                    SendMess.setVisibility(View.GONE);
                    tvsend.setVisibility(View.GONE);
                    CallVideo.setVisibility(View.GONE);
                    tvcall.setVisibility(View.GONE);
                } else {
                    SendMess.setVisibility(View.VISIBLE);
                    tvsend.setVisibility(View.VISIBLE);
                    CallVideo.setVisibility(View.VISIBLE);
                    tvcall.setVisibility(View.VISIBLE);
                    SendMess.setOnClickListener(view2 -> {

                    });
                    CallVideo.setOnClickListener(view2 -> {

                    });
                }
                logo.setOnClickListener(view1 -> {
                    callBalloon();
                    balloon.showAlignBottom(logo);
                });
            });
            v.setOnLongClickListener(view -> {
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.view_image_user);
                dialog.getWindow().setGravity(Gravity.CENTER_VERTICAL);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                photoView = dialog.findViewById(R.id.userInfo);
                tai_hinh_anh = dialog.findViewById(R.id.tai_hinh_anh);
                Bitmap info2 = getBitmapFromString(message.getImgProfile());
                photoView.setImageBitmap(info2);
                tai_hinh_anh.setOnClickListener(view1 -> {
                    Bitmap info1 = getBitmapFromString(message.getImgProfile());
                    saveImageToExternalStorage(info1);
                    MotionToast.Companion.darkToast((Activity) context,
                            "Save image",
                            "Image has save to internal !",
                            MotionToast.TOAST_SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.SHORT_DURATION,
                            getFont(context, R.font.helvetica_regular));
                });
                dialog.show();
                return true;
            });
        });
    }

    private void saveImageToExternalStorage(Bitmap bitmap) {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String name = "Image_" + n + ".jpg";
        File file = new File(myDir, name);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        MediaScannerConnection.scanFile(context, new String[]{file.toString()}, null,
                (path, uri) -> {
                    Log.i("ExternalStorage", "Scanned " + path + ":");
                    Log.i("ExternalStorage", "-> uri=" + uri);
                });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    private Bitmap getBitmapFromString(String image) {
        byte[] bytes = Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    private void callBalloon() {
        balloon = new Balloon.Builder(context)
                .setArrowSize(10)
                .setArrowOrientation(ArrowOrientation.TOP)
                .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
                .setArrowPosition(0.7f)
                .setWidth(BalloonSizeSpec.WRAP)
                .setHeight(65)
                .setTextSize(13f)
                .setCornerRadius(9f)
                .setAlpha(0.8f)
                .setPadding(10)
                .setText("This is image from this user")
                .setIconDrawable(ContextCompat.getDrawable(context, R.drawable.edit))
                .setTextColor(ContextCompat.getColor(context, R.color.white))
                .setTextIsHtml(true)
                .setAutoDismissDuration(2000L)
                .setBackgroundColor(ContextCompat.getColor(context, R.color.blue))
                .setBalloonAnimation(BalloonAnimation.FADE)
                .build();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ItemClickListener itemClickListener;
        ConstraintLayout constraintLayout;
        CircleImageView user;
        TextView name;

        public ViewHolder(@NonNull View view) {
            super(view);
            constraintLayout = view.findViewById(R.id.constraint);
            user = view.findViewById(R.id.user);
            itemView.setOnClickListener(this);
            name = view.findViewById(R.id.name);
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

