package com.techease.speed;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    TextView tvAlreadyMemberLogin;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
//
//        tvAlreadyMemberLogin = findViewById(R.id.tv_already_member_login);
//
//        tvAlreadyMemberLogin.setHighlightColor(getColor(R.color.login_text_color));

    }
}
