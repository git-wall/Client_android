package com.example.client_chat.Activity;

import static androidx.core.content.res.ResourcesCompat.getFont;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.example.client_chat.R;
import com.github.drjacky.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.skydoves.balloon.ArrowOrientation;
import com.skydoves.balloon.ArrowPositionRules;
import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;
import com.skydoves.balloon.BalloonSizeSpec;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import www.sanju.motiontoast.MotionToast;

public class ChooseActivity extends AppCompatActivity {
    boolean isChecked = false;
    LottieAnimationView nextButton;
    FloatingActionButton changeInformation;
    private Balloon balloon;
    CircleImageView circleImageView;
    String d;
    ActivityResultLauncher<Intent> launcher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (ActivityResult result) -> {
                if (result.getResultCode() == RESULT_OK) {
                    assert result.getData() != null;
                    Uri uri;
                    uri = result.getData().getData();
                    // Use the uri to load the image
                    if (uri == null) {
                        return;
                    } else {
                        circleImageView.setImageURI(uri);
                        try (FileInputStream os = (FileInputStream) getContentResolver().openInputStream(uri)) {
                            Bitmap image = BitmapFactory.decodeStream(os);
                            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                            image.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
                            d = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (result.getResultCode() == ImagePicker.RESULT_ERROR) {
                    ImagePicker.Companion.getError(result.getData());
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        nextButton = findViewById(R.id.nextButton);
        changeInformation = findViewById(R.id.changeInformation);
        circleImageView = findViewById(R.id.profile_image);
        Intent intent = getIntent();
        String a = intent.getStringExtra("username");
        int b = intent.getIntExtra("gender", 3);
        String date = intent.getStringExtra("date");
        changeInformation.setOnClickListener(view -> ImagePicker.Companion.with(this)
                .crop()
                .cropOval()
                .maxResultSize(512, 512, true)
                .createIntentFromDialog((Function1) (new Function1() {
                    public Object invoke(Object var1) {
                        this.invoke((Intent) var1);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(@NotNull Intent it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        launcher.launch(it);
                    }
                })));
        circleImageView.setOnClickListener(view -> {
            callBalloon();
            balloon.showAlignTop(circleImageView);
        });
        nextButton.setOnClickListener(view -> {
            nextButton.setSpeed(1);
            nextButton.playAnimation();
            isChecked = true;
            new Handler().postDelayed(() -> {
                if (d == null) {
                    MotionToast.Companion.darkToast(ChooseActivity.this,
                            "Error 404",
                            "You need chose image",
                            MotionToast.TOAST_ERROR,
                            MotionToast.GRAVITY_CENTER,
                            MotionToast.LONG_DURATION,
                            getFont(ChooseActivity.this, R.font.helvetica_regular));
                } else {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    intent1.putExtra("ten", a);
                    intent1.putExtra("gt", b);
                    intent1.putExtra("profile", d);
                    intent1.putExtra("date",date);
                    Bundle c = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
                    startActivity(intent1, c);
                    finish();
                }
            }, 2000);

        });
    }

    private void callBalloon() {
        balloon = new Balloon.Builder(this)
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
                .setText("You can set image in here")
                .setIconDrawable(ContextCompat.getDrawable(this, R.drawable.edit))
                .setTextColor(ContextCompat.getColor(this, R.color.white))
                .setTextIsHtml(true)
                .setAutoDismissDuration(2000L)
                .setBackgroundColor(ContextCompat.getColor(this, R.color.blue))
                .setBalloonAnimation(BalloonAnimation.FADE)
                .build();
    }

}