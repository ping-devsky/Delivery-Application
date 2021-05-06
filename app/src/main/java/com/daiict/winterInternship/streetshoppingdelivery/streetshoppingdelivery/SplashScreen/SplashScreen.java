package com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.daiict.winterInternship.streetshoppingdelivery.R;
import com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.IntroSlider.introSlider;
import com.daiict.winterInternship.streetshoppingdelivery.streetshoppingdelivery.SignupSignIn.Signin;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    //above value is in milisecs

    //creating variable for animation
    Animation top, bottom , side;

    //Creating Objects of layouts
    ImageView imageView;
    TextView txttag, txtini;

    //Shared Peferences for letting know the user entry
    SharedPreferences shared_introslider;

//    private String email;
//    private String pass;
//    private int role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        //Removing Status bar from the top
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        top = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        side = AnimationUtils.loadAnimation(this, R.anim.side_animation);

        imageView = findViewById(R.id.imageView);
        txttag = findViewById(R.id.txtag);
        txtini = findViewById(R.id.txtini);

        //Assigning animations
        imageView.setAnimation(side);
        txtini.setAnimation(bottom);
        txttag.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @SuppressLint("ApplySharedPref")
            @Override
            public void run() {
                shared_introslider = getSharedPreferences("introSlider", MODE_PRIVATE);
                boolean isfirsttimeUser = shared_introslider.getBoolean("isfirsttime", true);
                if (isfirsttimeUser) {
                    //After getting first time as true for the next time when user arrives make it false
                    SharedPreferences.Editor editor = shared_introslider.edit();
                    editor.putBoolean("isfirsttime", false);
                    editor.commit();


                    Intent intent = new Intent(SplashScreen.this, introSlider.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashScreen.this, Signin.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }

            }
        }, SPLASH_SCREEN);

    }
}