package com.techease.speedracerz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.techease.speed.BookingActivity;
import com.techease.speedracerz.firebase.MyJobService;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, MyJobService.class));

        startActivity(new Intent(this, BookingActivity.class));
    }
}
