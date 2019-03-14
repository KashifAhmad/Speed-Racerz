package com.techease.speedracerz.views;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.techease.speed.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.til_login_username)
    TextInputLayout tilLoginUserName;
    @BindView(R.id.et_login_username)
    EditText etLoginUsername;
    @BindView(R.id.til_login_password)
    TextInputLayout tilLoginPassword;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.tv_new_here_register)
    TextView tvNewHereRegister;


    @BindView(R.id.iv_login_facebook)
    ImageView ivLoginFacebook;
    @BindView(R.id.iv_login_google)
    ImageView ivLoginGoogle;
    @BindView(R.id.iv_login_twitter)
    ImageView ivLoginTwitter;
    @BindView(R.id.iv_login_instagram)
    ImageView ivLoginInstagrarm;


    String strLoginUsername, strLoginPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


        buttonClickListener();


    }

    private void buttonClickListener() {
        btnLogin.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);
        tvNewHereRegister.setOnClickListener(this);

        ivLoginFacebook.setOnClickListener(this);
        ivLoginGoogle.setOnClickListener(this);
        ivLoginTwitter.setOnClickListener(this);
        ivLoginInstagrarm.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_login:

                if (validate()) {

                }
                break;
            case R.id.tv_forget_password:

                break;
            case R.id.tv_new_here_register:

                finish();
                startActivity(new Intent(this, RegistrationActivity.class));
                break;


            case R.id.iv_login_facebook:

                break;

            case R.id.iv_login_google:

                break;

            case R.id.iv_login_twitter:

                break;

            case R.id.iv_login_instagram:

                break;
        }

    }


    private boolean validate() {
        boolean valid = true;

        strLoginUsername = etLoginUsername.getText().toString();
        strLoginPassword = etLoginPassword.getText().toString();

        if (strLoginUsername.isEmpty()) {
            tilLoginUserName.setErrorEnabled(true);
            tilLoginUserName.setError("enter a valid username");
            valid = false;
        } else {
            tilLoginUserName.setErrorEnabled(false);
        }
        if (strLoginPassword.isEmpty() || strLoginPassword.length() < 4 || strLoginPassword.length() > 12) {
            tilLoginPassword.setErrorEnabled(true);
            tilLoginPassword.setError("between 4 and 12 alphanumeric characters");
            valid = false;
        } else {
            tilLoginPassword.setErrorEnabled(false);
        }

        return valid;

    }
}
