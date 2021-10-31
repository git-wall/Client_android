package com.example.client_chat.Activity;

import static androidx.core.content.res.ResourcesCompat.getFont;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.client_chat.R;

import java.util.Calendar;

import www.sanju.motiontoast.MotionToast;

public class DatePickerActivity extends AppCompatActivity {
    DatePickerDialog.OnDateSetListener listener;
    TextView dataDatetime;
    LottieAnimationView next,birthDay;
    public String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        Mirror();
        Intent intent = getIntent();
        String a = intent.getStringExtra("username");
        int b = intent.getIntExtra("gender", 3);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        birthDay.setOnClickListener(view -> {
            DatePickerDialog dialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,listener,year,month,day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });
        listener = (view, year1, month1, day1) -> {
            month1 += 1;
            date = day1 + "/" + month1 + "/" + year1;
            dataDatetime.setText(date);
        };
        next.setOnClickListener(view -> {
            next.setSpeed(1);
            next.playAnimation();
            if(TextUtils.isEmpty(dataDatetime.getText().toString().trim())){
                MotionToast.Companion.darkToast(this,
                        "Error 404",
                        "You need to chose in your birthday",
                        MotionToast.TOAST_ERROR,
                        MotionToast.GRAVITY_CENTER,
                        MotionToast.LONG_DURATION,
                        getFont(this, R.font.helvetica_regular));
                return;
            }else {
                Intent intent1 = new Intent(this, ChooseActivity.class);
                intent1.putExtra("username", a);
                intent1.putExtra("gender", b);
                intent1.putExtra("date",date);
                Bundle c = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
                startActivity(intent1, c);
                finish();
            }
        });
    }
    private void Mirror(){
        dataDatetime = findViewById(R.id.dataDatetime);
        next = findViewById(R.id.next1);
        birthDay = findViewById(R.id.birthDay);
    }
}