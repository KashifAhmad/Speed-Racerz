package com.techease.speedracerz.views;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.techease.speed.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserRegistrationActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.btn_user_register)
    Button btnUserRegister;

    @BindView(R.id.et_user_username)
    EditText etUsername;
    @BindView(R.id.til_user_username)
    TextInputLayout tilUsername;

    @BindView(R.id.et_user_email_address)
    EditText etEmailAddress;
    @BindView(R.id.til_user_email)
    TextInputLayout tilEmail;

    @BindView(R.id.et_user_password)
    EditText etPassword;
    @BindView(R.id.til_user_password)
    TextInputLayout tilPassword;


    @BindView(R.id.et_user_country)
    EditText etCountry;
    @BindView(R.id.til_user_country)
    TextInputLayout tilCountry;
    @BindView(R.id.et_user_city)
    EditText etCity;
    @BindView(R.id.til_user_city)
    TextInputLayout tillCity;
    @BindView(R.id.et_user_company)
    EditText etCompany;
    @BindView(R.id.til_user_company)
    TextInputLayout tillCompany;


    @BindView(R.id.tv_already_member_login)
    TextView tvAlreadyMemberLogin;


    String strUsername, strEmail, strPassword, strCountry, strCity, strCompany;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        ButterKnife.bind(this);


        initiateClick();
    }

    private void initiateClick() {

        btnUserRegister.setOnClickListener(this);
        tvAlreadyMemberLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_user_register:

                if (validate()) {
                    startActivity(new Intent(this, LocationAccessActivity.class));
                }
                break;
            case R.id.tv_already_member_login:
                finish();
                startActivity(new Intent(this, LocationAccessActivity.class));
                break;
        }
    }

    private boolean validate() {
        boolean valid = true;

        strUsername = etUsername.getText().toString();
        strEmail = etEmailAddress.getText().toString();
        strPassword = etPassword.getText().toString();
        strCountry = etCountry.getText().toString();
        strCity = etCity.getText().toString();
        strCompany = etCompany.getText().toString();
        strCity = etCity.getText().toString();
        strCompany = etCompany.getText().toString();


        if (strUsername.isEmpty() || strUsername.length() < 3) {
            tilUsername.setError("enter a valid username");
            valid = false;
        } else {
            tilUsername.setError(null);
        }

        if (strEmail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
            tilEmail.setError("enter a valid email address");
            valid = false;
        } else {
            tilEmail.setError(null);
        }
        if (strPassword.isEmpty() || strPassword.length() < 4 || strPassword.length() > 12) {
            tilPassword.setError("between 4 and 12 alphanumeric characters");
            valid = false;
        } else {
            tilPassword.setError(null);
        }
        if (strCity.isEmpty() || strCity.length() < 3) {
            tillCity.setError("enter a city");
            valid = false;
        } else {
            tillCity.setError(null);
        }
        if (strCompany.isEmpty() || strCompany.length() < 3) {
            tillCompany.setError("enter country");
            valid = false;
        } else {
            tillCompany.setError(null);
        }

        return valid;

    }
}
