package com.techease.speedracerz.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.techease.speedracerz.R;


import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationAccessActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.btn_location_access_allow)
    Button btnLocationAccessAllow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_access);


      ButterKnife.bind(this);
        btnLocationAccessAllow.setOnClickListener(this);

}

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_location_access_allow:
                startActivity(new Intent(this, NotificationAccessActivity.class));
                break;
        }

    }

}
