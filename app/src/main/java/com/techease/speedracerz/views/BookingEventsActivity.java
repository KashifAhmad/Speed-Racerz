package com.techease.speedracerz.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.techease.speedracerz.R;
import com.techease.speedracerz.utils.SharedPrefUtils;

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
    private String bookedBy, phoneNumber, zipCode;
    private boolean valid = false;

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
                if (isValid()) {
                    SharedPrefUtils.getEditor(this).putString("bookedBy", bookedBy).commit();
                    SharedPrefUtils.getEditor(this).putString("bookedPhoneNumber", phoneNumber).commit();
                    SharedPrefUtils.getEditor(this).putString("zipCode", zipCode).commit();
                    startActivity(new Intent(this, QuantityEventsActivity.class));
                }
                break;


        }

    }

    private boolean isValid() {
        valid = true;
        bookedBy = etBookedBy.getText().toString();
        phoneNumber = etPhoneNumber.getText().toString();
        zipCode = etZipCode.getText().toString();
        if (bookedBy.isEmpty()) {
            tilBooked.setErrorEnabled(true);
            tilBooked.setError("Please write your name");

            valid = false;
        } else {
            tilBooked.setError(null);
            tilBooked.setErrorEnabled(false);
        }
        if (phoneNumber.isEmpty()) {
            tilPhone.setError("Please write your phone number");
            tilPhone.setErrorEnabled(true);
            valid = false;
        } else {
            tilPhone.setError(null);
            tilPhone.setErrorEnabled(false);
        }
        if (zipCode.isEmpty()) {
            tilZip.setErrorEnabled(true);
            tilZip.setError("Please write your zip code");
            valid = false;
        } else {
            tilZip.setErrorEnabled(false);
            tilZip.setError(null);
        }
        return valid;
    }
}
