package com.techease.speedracerz.views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.techease.speed.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {


    @BindView(R.id.navigationView)
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_shape:


                    return true;
                case R.id.navigation_events:
                    startActivity(new Intent(ProfileActivity.this, EventActivity.class));


                    return true;
                case R.id.navigation_group:


                    return true;
                case R.id.navigation_profile:

                    startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
                    return true;
            }
            return false;
        }
    };
}
