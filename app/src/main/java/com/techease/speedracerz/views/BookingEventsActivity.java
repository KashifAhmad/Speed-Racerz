package com.techease.speedracerz.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.techease.speedracerz.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BookingEventsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_continue_booking_events)
    Button btnContinueBookingEvents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);

        btnContinueBookingEvents.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_continue_booking_events:
                startActivity(new Intent(this, PaymentEventsActivity.class));

        }

    }
}
