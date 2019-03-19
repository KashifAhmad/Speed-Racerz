package com.techease.speedracerz.views;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.techease.speed.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.rl_user)
    RelativeLayout rlUser;
    @BindView(R.id.rl_racer)
    RelativeLayout rlRacer;

    @BindView(R.id.et_registration_username)
    EditText etRegistrationUsername;

    @BindView(R.id.til_registration_username)
    TextInputLayout tilRegistrationUsername;

    @BindView(R.id.et_registration__email_address)
    EditText etRegistrationEmailAddress;

    @BindView(R.id.til_registration_email)
    TextInputLayout tilRegistrationEmail;

    @BindView(R.id.et_registration_password)
    EditText etRegistrationPassword;

    @BindView(R.id.til_registration_password)
    TextInputLayout tilRegistrationPassword;

    @BindView(R.id.tv_already_member_login)
    TextView tvAlreadyMemberLogin;

    @BindView(R.id.btn_register)
    Button btnRegister;


    String strRegistrationUsername, strRegistrationEmailAddress, strRegistrationPassword, strIAm;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);


        btnRegister.setOnClickListener(this);
        rlUser.setOnClickListener(this);
        rlRacer.setOnClickListener(this);
        tvAlreadyMemberLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.btn_register:
                if (validate()) {
                    if (strIAm == null) {
                        Toast.makeText(this, "racer or user not selected", Toast.LENGTH_SHORT).show();
                    } else if (strIAm.equals("user")) {
                        startActivity(new Intent(this, UserRegistrationActivity.class));

                    } else if (strIAm.equals("racer")) {

                        startActivity(new Intent(this, RacerRegistrationActivity.class));
                    }

                }

                break;
            case R.id.tv_already_member_login:

                finish();
                startActivity(new Intent(this, LoginActivity.class));

                break;

            case R.id.rl_user:
                strIAm = "user";

                rlRacer.setBackgroundColor(getResources().getColor(R.color.round_gry_color));
                rlUser.setBackgroundColor(getResources().getColor(R.color.brown_text_color));

                break;
            case R.id.rl_racer:
                strIAm = "racer";
                rlUser.setBackgroundColor(getResources().getColor(R.color.round_gry_color));
                rlRacer.setBackgroundColor(getResources().getColor(R.color.brown_text_color));

                break;
        }

    }

    private boolean validate() {
        boolean valid = true;

        strRegistrationUsername = etRegistrationUsername.getText().toString();
        strRegistrationEmailAddress = etRegistrationEmailAddress.getText().toString();
        strRegistrationPassword = etRegistrationPassword.getText().toString();


        if (strRegistrationUsername.isEmpty() || strRegistrationUsername.length() < 3) {

            tilRegistrationUsername.setErrorEnabled(true);
            tilRegistrationUsername.setError("enter a valid username");
            valid = false;
        } else {
            tilRegistrationUsername.setError(null);
        }

        if (strRegistrationEmailAddress.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(strRegistrationEmailAddress).matches()) {

            tilRegistrationUsername.setErrorEnabled(true);
            tilRegistrationEmail.setError("enter a valid email address");
            valid = false;
        } else {
            tilRegistrationEmail.setError(null);
        }
        if (strRegistrationPassword.isEmpty() || strRegistrationPassword.length() < 4 || strRegistrationPassword.length() > 12) {


            tilRegistrationUsername.setErrorEnabled(true);
            tilRegistrationPassword.setError("between 4 and 12 alphanumeric characters");
            valid = false;
        } else {
            tilRegistrationPassword.setError(null);
        }

        return valid;

    }
}
