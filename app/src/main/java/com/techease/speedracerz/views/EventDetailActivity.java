package com.techease.speedracerz.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.techease.speedracerz.R;
import com.techease.speedracerz.dataModels.eventDetailModels.EventDetailsResponseModel;
import com.techease.speedracerz.networking.BaseNetworking;
import com.techease.speedracerz.utils.SharedPrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailActivity extends AppCompatActivity implements View.OnClickListener {



    @BindView(R.id.btn_join_events)
    Button btnEvent;
    int eventID;
    @BindView(R.id.tv_event_title)
    TextView tvEventTitle;
    @BindView(R.id.tv_event_type)
    TextView tvEventType;
    @BindView(R.id.tv_event_description)
    TextView tvDescription;
    @BindView(R.id.tv_user_name)
    TextView tvUsername;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_description)
    TextView tvDetailDescription;
    @BindView(R.id.iv_event_image)
    ImageView ivEventImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        initUI();

    }

    private void initUI() {
        ButterKnife.bind(this);
        eventID = SharedPrefUtils.getSharedPref(this).getInt("event_id", 0);
        btnEvent.setOnClickListener(this);
        getEventDetails();

    }

    private void getEventDetails() {
        Call<EventDetailsResponseModel> call = BaseNetworking.apiServices(SharedPrefUtils.getApiToken(this))
                .getEventDetails(eventID);
        call.enqueue(new Callback<EventDetailsResponseModel>() {
            @Override
            public void onResponse(Call<EventDetailsResponseModel> call, Response<EventDetailsResponseModel> response) {
                if (response.isSuccessful()){
                    tvEventType.setText(response.body().getData().getCategory());
                    tvEventTitle.setText(response.body().getData().getTitle());
                    tvDescription.setText(response.body().getData().getDescription());
//                    tvLocation.setText(response.body().getData().getTickets().getRacers().get(0).getCity());
                    tvUsername.setText(SharedPrefUtils.getSharedPref(EventDetailActivity.this).getString("name","" ));
                    SharedPrefUtils.getEditor(EventDetailActivity.this).putInt("eventID", eventID).commit();
                    Picasso.get().load(response.body().getData().getImage()).into(ivEventImage);

                }
            }

            @Override
            public void onFailure(Call<EventDetailsResponseModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_join_events:
                startActivity(new Intent(this, BookingEventsActivity.class));

        }

    }
}
