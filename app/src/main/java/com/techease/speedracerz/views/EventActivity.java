package com.techease.speedracerz.views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.techease.speed.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.iv_event)
    ImageView ivEvent;

    @BindView(R.id.navigationView)
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        ivEvent.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_event:
                startActivity(new Intent(this, AboutEventsActivity.class));

        }

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_shape:


                    return true;
                case R.id.navigation_events:
                    startActivity(new Intent(EventActivity.this, EventActivity.class));


                    return true;
                case R.id.navigation_group:


                    return true;
                case R.id.navigation_profile:

                    startActivity(new Intent(EventActivity.this, ProfileActivity.class));
                    return true;
            }
            return false;
        }
    };

}
