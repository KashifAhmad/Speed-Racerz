package com.techease.speedracerz.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.techease.speedracerz.R;
import com.techease.speedracerz.utils.SharedPrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewEventsActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.btn_buy_now)
    Button btnBuyNow;

    @BindView(R.id.tv_booking_qty)
    TextView tvBookingQuantity;
    @BindView(R.id.tv_booking_amount)
    TextView tvBookingAmount;
    @BindView(R.id.tv_email)
    TextView tvEmail;

    @BindView(R.id.tv_edit_email)
    TextView tvEditEmail;

    @BindView(R.id.tv_booked_by)
    TextView tvBookedBy;

    @BindView(R.id.tv_edit_booked_by)
    TextView tvEditBookedBy;

    @BindView(R.id.tv_address)
    TextView tvAddress;

    @BindView(R.id.tv_card_expiry)
    TextView tvCardExpiry;

    @BindView(R.id.tv_edit_card)
    TextView tvEditCard;

    @BindView(R.id.tv_sub_total)
    TextView tvSubtotal;

    @BindView(R.id.tv_tax)
    TextView tvTax;
    @BindView(R.id.tv_discount)
    TextView tvDiscount;
    @BindView(R.id.tv_grand_total)
    TextView tvGrandTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        initUI();
    }

    private void initUI() {
        ButterKnife.bind(this);
        initData();
        btnBuyNow.setOnClickListener(this);

    }

    private void initData() {
        tvBookingQuantity.setText(SharedPrefUtils.getSharedPref(this).getString("quantity", ""));
        tvBookingAmount.setText(SharedPrefUtils.getSharedPref(this).getString("changedPrice", ""));
        tvEmail.setText(SharedPrefUtils.getSharedPref(this).getString("email", ""));
        tvAddress.setText(SharedPrefUtils.getSharedPref(this).getString("address", ""));
        tvBookedBy.setText(SharedPrefUtils.getSharedPref(this).getString("bookedBy", ""));
        tvCardExpiry.setText(SharedPrefUtils.getSharedPref(this).getString("cardExpiry", ""));
        tvSubtotal.setText(SharedPrefUtils.getSharedPref(this).getString("changedPrice", ""));
//        tvTax.setText(SharedPrefUtils.getSharedPref(this).getString("changedPrice", ""));
        tvGrandTotal.setText(SharedPrefUtils.getSharedPref(this).getString("totalPrice", ""));
        tvDiscount.setText(SharedPrefUtils.getSharedPref(this).getString("discount", ""));

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_buy_now:
                startActivity(new Intent(this, MyTicketsActivity.class));

        }

    }
}
