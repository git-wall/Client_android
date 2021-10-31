package com.example.client_chat.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.client_chat.R;
import com.example.client_chat.Surface.Rotation3DSample;
import com.kiprotich.japheth.TextAnim;

import su.levenetc.android.textsurface.TextSurface;

public class IntroActivity extends AppCompatActivity {
    private TextSurface textSurface;
    private TextAnim textWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        textSurface = findViewById(R.id.textSurface);
        textWriter = findViewById(R.id.textWriter);
        textWriter
                .setWidth(12)
                .setDelay(30)
                .setColor(Color.BLACK)
                .setConfig(TextAnim.Configuration.INTERMEDIATE)
                .setSizeFactor(35f)
                .setLetterSpacing(25f)
                .setText("ALO CHAT")
                .setListener(() -> {
                    //do stuff after animation is finished
                })
                .startAnimation();
        textSurface.postDelayed(new Runnable() {
            @Override
            public void run() {
                show();
                new Handler().postDelayed(() -> {
                    Intent intent = new Intent(getApplicationContext(), AddUserActivity.class);
                    Bundle c = ActivityOptions.makeSceneTransitionAnimation(IntroActivity.this).toBundle();
                    startActivity(intent, c);
                    finish();
                }, 6500);
            }
        }, 1000);
    }

    private void show() {
        //vip
        Rotation3DSample.play(textSurface);

//        SurfaceScaleSample.play(textSurface);
//        CookieThumperSample.play(textSurface, getAssets());
    }
}