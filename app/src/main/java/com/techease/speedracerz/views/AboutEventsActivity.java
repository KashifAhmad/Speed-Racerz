package com.techease.speedracerz.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.techease.speed.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutEventsActivity extends AppCompatActivity implements View.OnClickListener {



    @BindView(R.id.btn_join_events)
    Button btnEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_events);
        ButterKnife.bind(this);

        btnEvent.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_join_events:
                startActivity(new Intent(this, BookingEventsActivity.class));

        }

    }
}
