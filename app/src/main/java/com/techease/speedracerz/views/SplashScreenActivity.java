package com.techease.speedracerz.views;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.techease.speed.R;
import com.techease.speedracerz.utils.GeneralUtils;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                if(GeneralUtils.isLogin(SplashScreenActivity.this)){
                    startActivity(new Intent(SplashScreenActivity.this, EventActivity.class));
                }
                else {
                    startActivity(new Intent(SplashScreenActivity.this, LogInChoiceActivity.class));
                }

            }
        }, 1000);


    }
}
