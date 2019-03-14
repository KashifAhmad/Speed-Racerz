package com.techease.speedracerz.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.techease.speed.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllDoneWelcomeActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.btn_all_clear_done)
    Button btnAllClearDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_done_welcome);

        ButterKnife.bind(this);
        btnAllClearDone.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_all_clear_done:
                startActivity(new Intent(this, EventActivity.class));
                break;
        }

    }

}
