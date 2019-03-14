package com.techease.speedracerz.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.techease.speed.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewEventsActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.btn_buy_now)
    Button btnBuyNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        ButterKnife.bind(this);

        btnBuyNow.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_buy_now:
                startActivity(new Intent(this, MyTicketsActivity.class));

        }

    }
}
