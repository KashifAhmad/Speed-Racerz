package com.techease.speedracerz.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.techease.speedracerz.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationAccessActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.btn_notification_access_allow)
    Button btnNotificationAccessAllow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_access);

        ButterKnife.bind(this);
        btnNotificationAccessAllow.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_notification_access_allow:
                startActivity(new Intent(this, AllDoneWelcomeActivity.class));
                break;
        }

    }

}
