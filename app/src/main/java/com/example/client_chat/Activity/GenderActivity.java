package com.example.client_chat.Activity;

import static androidx.core.content.res.ResourcesCompat.getFont;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.airbnb.lottie.LottieAnimationView;
import com.example.client_chat.R;

import www.sanju.motiontoast.MotionToast;

public class GenderActivity extends AppCompatActivity {
    LottieAnimationView man, woman,nextLotti;;
    AppCompatButton next;
    int gioitinh;
    RadioGroup rdogroup;
    RadioButton rdonam, rdonu;
    LinearLayout line_nam, linear_nu;

    public GenderActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        Mirror();
        Intent intent = getIntent();
        String a = intent.getStringExtra("name");
        rdogroup.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == R.id.rdinam) {
                gioitinh = 1;
                line_nam.setBackgroundResource(R.drawable.mbackground_black);
                linear_nu.setBackgroundResource(R.drawable.mbackground);
            } else {
                gioitinh = 0;
                linear_nu.setBackgroundResource(R.drawable.mbackground_black);
                line_nam.setBackgroundResource(R.drawable.mbackground);
            }
        });
        nextLotti.setOnClickListener(view -> {
            nextLotti.setSpeed(1);
            nextLotti.playAnimation();
            if (rdonam.isChecked()) {
                gioitinh = 1;
                Intent intent1 = new Intent(GenderActivity.this, DatePickerActivity.class);
                intent1.putExtra("username", a);
                intent1.putExtra("gender", gioitinh);
                Bundle c = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
                startActivity(intent1, c);
                finish();
            } else if (rdonu.isChecked()) {
                gioitinh = 0;
                Intent intent1 = new Intent(GenderActivity.this, DatePickerActivity.class);
                intent1.putExtra("username", a);
                intent1.putExtra("gender", gioitinh);
                Bundle c = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
                startActivity(intent1, c);
                finish();
            } else {
                MotionToast.Companion.darkToast(GenderActivity.this,
                        "Error 404",
                        "You need to fill in your gender information ",
                        MotionToast.TOAST_ERROR,
                        MotionToast.GRAVITY_CENTER,
                        MotionToast.LONG_DURATION,
                        getFont(GenderActivity.this, R.font.helvetica_regular));
                return;
            }
        });
    }

    public void Mirror() {
        line_nam = findViewById(R.id.line_nam);
        linear_nu = findViewById(R.id.linear_nu);
        man = findViewById(R.id.man);
        woman = findViewById(R.id.woman);
//        next = findViewById(R.id.);
        rdonam = findViewById(R.id.rdinam);
        rdonu = findViewById(R.id.rdinu);
        rdogroup = findViewById(R.id.rdogroup);
        nextLotti = findViewById(R.id.next);
    }
}