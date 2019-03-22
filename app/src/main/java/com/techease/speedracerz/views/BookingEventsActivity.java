package com.techease.speedracerz.views;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.techease.speedracerz.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BookingEventsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_continue_booking_events)
    Button btnContinueBookingEvents;

    @BindView(R.id.til_booked_by)
    TextInputLayout tilBooked;
    @BindView(R.id.et_booked_by)
    EditText etBookedBy;
    @BindView(R.id.til_phone)
    TextInputLayout tilPhone;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.til_zip_code)
    TextInputLayout tilZip;
    @BindView(R.id.et_zip_code)
    EditText etZipCode;

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
