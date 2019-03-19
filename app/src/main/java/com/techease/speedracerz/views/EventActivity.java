package com.techease.speedracerz.views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.techease.speed.R;
import com.techease.speedracerz.adapters.EventAdapter;
import com.techease.speedracerz.dataModels.eventsDataModels.EventDataModel;
import com.techease.speedracerz.dataModels.eventsDataModels.EventResponseModel;
import com.techease.speedracerz.dataModels.loginModels.LoginResponse;
import com.techease.speedracerz.networking.APIClient;
import com.techease.speedracerz.networking.APIServices;
import com.techease.speedracerz.utils.AlertUtils;
import com.techease.speedracerz.utils.GeneralUtils;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity {
    AlertDialog alertDialog;
    @BindView(R.id.rv_event)
    RecyclerView rvEvents;
    @BindView(R.id.navigationView)
    BottomNavigationView bottomNavigationView;

    ArrayList<EventDataModel> eventDataModelArrayList;
    EventAdapter eventAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initUI();

    }

    private void initUI() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvEvents.setLayoutManager(linearLayoutManager);
        eventDataModelArrayList = new ArrayList<>();
        alertDialog = AlertUtils.createProgressDialog(this);
        alertDialog.show();
        eventAdapter = new EventAdapter(this, eventDataModelArrayList);
        rvEvents.setAdapter(eventAdapter);
        apiCallEvents();
    }

    private void apiCallEvents() {
        APIServices services = APIClient.getClient(GeneralUtils.getApiToken(this)).create(APIServices.class);
        Call<EventResponseModel> userLogin = services.events();
        userLogin.enqueue(new Callback<EventResponseModel>() {
            @Override
            public void onResponse(Call<EventResponseModel> call, Response<EventResponseModel> response) {
                alertDialog.dismiss();
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(EventActivity.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(EventActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else if (response.body().getStatus()) {
                   eventDataModelArrayList.addAll(response.body().getData());
                   eventAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<EventResponseModel> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(EventActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_shape:


                    return true;
                case R.id.navigation_events:
                    startActivity(new Intent(EventActivity.this, EventActivity.class));


                    return true;
                case R.id.navigation_group:


                    return true;
                case R.id.navigation_profile:

                    startActivity(new Intent(EventActivity.this, ProfileActivity.class));
                    return true;
            }
            return false;
        }
    };

}
