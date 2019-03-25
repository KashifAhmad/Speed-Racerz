package com.techease.speedracerz.views;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.techease.speedracerz.R;
import com.techease.speedracerz.firebase.MyJobService;
import com.techease.speedracerz.utils.GeneralUtils;
import com.techease.speedracerz.utils.SharedPrefUtils;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Log.d("zma oauth token", SharedPrefUtils.getApiToken(this));
        startService( new Intent(this, MyJobService.class));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                startActivity(new Intent(SplashScreenActivity.this, LogInChoiceActivity.class));
                if(GeneralUtils.isLogin(SplashScreenActivity.this)){
                    startActivity(new Intent(SplashScreenActivity.this, BottomNavActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(SplashScreenActivity.this, LogInChoiceActivity.class));
                    finish();
                }

            }
        }, 1000);


    }
}
