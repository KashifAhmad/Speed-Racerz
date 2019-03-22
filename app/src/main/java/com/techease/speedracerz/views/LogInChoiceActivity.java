package com.techease.speedracerz.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.techease.speedracerz.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInChoiceActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.tv_login_choice)
    TextView tvLogin;
    @BindView(R.id.tv_registration_choice)
    TextView tvRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_choise);
        ButterKnife.bind(this);
        tvLogin.setOnClickListener(this);
        tvRegistration.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_login_choice:

                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.tv_registration_choice:

                startActivity(new Intent(this, RacerRegistrationActivity.class));
                break;

        }

    }
}
