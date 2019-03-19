package com.techease.speedracerz.views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.techease.speed.R;
import com.techease.speedracerz.dataModels.loginModels.LoginResponse;
import com.techease.speedracerz.dataModels.profileDataModel.ProfileResponseModel;
import com.techease.speedracerz.networking.APIClient;
import com.techease.speedracerz.networking.APIServices;
import com.techease.speedracerz.utils.AlertUtils;
import com.techease.speedracerz.utils.GeneralUtils;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.iv_profile)
    ImageView ivProfile;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_country)
    TextView tvCountry;
    @BindView(R.id.iv_edit_profile)
    ImageView ivEditProfile;
    @BindView(R.id.navigationView)
    BottomNavigationView bottomNavigationView;

    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initUI();

    }

    private void initUI() {
        alertDialog = AlertUtils.createProgressDialog(this);
        alertDialog.show();
        apiCallProfile();

        ivEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void apiCallProfile() {
        APIServices services = APIClient.getClient(GeneralUtils.getApiToken(ProfileActivity.this)).create(APIServices.class);
        Call<ProfileResponseModel> userLogin = services.profile();
        userLogin.enqueue(new Callback<ProfileResponseModel>() {
            @Override
            public void onResponse(Call<ProfileResponseModel> call, Response<ProfileResponseModel> response) {
                alertDialog.dismiss();
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ProfileActivity.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else if (response.body().getStatus()) {
                    Picasso.get().load(response.body().getData().getProfilePicture()).into(ivProfile);
                    tvName.setText(response.body().getData().getName());
                    tvCountry.setText(response.body().getData().getCountry());
                }
            }

            @Override
            public void onFailure(Call<ProfileResponseModel> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
                    startActivity(new Intent(ProfileActivity.this, EventActivity.class));
                    return true;
                case R.id.navigation_group:
                    return true;
                case R.id.navigation_profile:
                    startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
                    return true;
            }
            return false;
        }
    };
}
