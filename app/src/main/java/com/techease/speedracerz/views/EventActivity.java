package com.techease.speedracerz.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.techease.speedracerz.R;
import com.techease.speedracerz.adapters.EventAdapter;
import com.techease.speedracerz.dataModels.eventsDataModels.EventDataModel;
import com.techease.speedracerz.utils.GeneralUtils;
import com.techease.speedracerz.views.fragments.ComingSoonFragment;
import com.techease.speedracerz.views.fragments.EventFragment;
import com.techease.speedracerz.views.fragments.ProfileFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventActivity extends AppCompatActivity {
    @BindView(R.id.navigationView)
    BottomNavigationView bottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_shape:
                    GeneralUtils.fragmentChanger(EventActivity.this, new ComingSoonFragment(), R.id.bottom_container);
                    return true;
                case R.id.navigation_events:
                    GeneralUtils.fragmentChanger(EventActivity.this, new EventFragment(), R.id.bottom_container);
                    return true;
                case R.id.navigation_group:
                    GeneralUtils.fragmentChanger(EventActivity.this, new ComingSoonFragment(), R.id.bottom_container);
                    return true;
                case R.id.navigation_profile:
                    GeneralUtils.fragmentChanger(EventActivity.this, new ProfileFragment(), R.id.bottom_container);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        initUI();
    }
    private void initUI() {
        ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigationView.setSelectedItemId(R.id.navigation_events);
    }

}
