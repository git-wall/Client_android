package com.example.client_chat.Activity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.client_chat.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class AddUserActivity extends AppCompatActivity {
    private AppCompatButton setNickName;
    private TextInputEditText userNickName;
    private TextInputLayout textInputLayout1;
    float v = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Mirror();
        Anim();
        userNickName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    setNickName.setEnabled(true);
                    setNickName.setText("Let chat");
                    setNickName.setBackgroundResource(R.drawable.button_color2);
                } else {
                    setNickName.setEnabled(false);
                    setNickName.setBackgroundResource(R.drawable.button);
                    setNickName.setText("Continue");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        setNickName.setOnClickListener(v -> {
            Intent intent = new Intent(AddUserActivity.this, GenderActivity.class);
            intent.putExtra("name", Objects.requireNonNull(userNickName.getText()).toString());
            Bundle c = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
            startActivity(intent,c);
            finish();
        });
    }

    public void Mirror() {
        userNickName = findViewById(R.id.userNickName);
        setNickName = findViewById(R.id.setNickName);
        textInputLayout1 = findViewById(R.id.textInputLayout1);
    }

    public void Anim() {
        userNickName.setTranslationY(300);
        setNickName.setTranslationY(300);
        textInputLayout1.setTranslationY(300);

        userNickName.setAlpha(v);
        setNickName.setAlpha(v);
        textInputLayout1.setAlpha(v);

        userNickName.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();
        setNickName.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();
        textInputLayout1.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();

    }
}
