package com.example.client_chat.Other;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.widget.AppCompatButton;

import com.airbnb.lottie.LottieAnimationView;
import com.example.client_chat.R;

public class NetworkChangeListener extends BroadcastReceiver {
    AppCompatButton appCompatButton;
    LottieAnimationView connecting;
    @Override
    public void onReceive(Context context, Intent intent) {

        if (!Common.isConnectedInternet(context)) {
            AlertDialog.Builder a = new AlertDialog.Builder(context);
            View v = LayoutInflater.from(context).inflate(R.layout.aleardialog, null);
            a.setView(v);
            AlertDialog b = a.create();
            b.setCanceledOnTouchOutside(false);
            b.getWindow().setGravity(Gravity.CENTER);
            b.setCanceledOnTouchOutside(false);
            b.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            b.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            b.show();
            connecting = v.findViewById(R.id.connected_button);
            connecting.setOnClickListener(view -> {
//                connecting.setSpeed(1);
//                connecting.playAnimation();
                b.dismiss();
                onReceive(context, intent);
            });
        }
    }
}
