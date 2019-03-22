package com.techease.speedracerz.views.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.techease.speedracerz.R;
import com.techease.speedracerz.dataModels.profileDataModel.ProfileResponseModel;
import com.techease.speedracerz.networking.APIClient;
import com.techease.speedracerz.networking.APIServices;
import com.techease.speedracerz.utils.AlertUtils;
import com.techease.speedracerz.utils.GeneralUtils;
import com.techease.speedracerz.views.SplashScreenActivity;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {
    View view;
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
    @BindView(R.id.btn_logout)
    Button btnLogout;

    AlertDialog alertDialog;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initUI();
        return view;
    }

    private void initUI() {
        ButterKnife.bind(this, view);
        alertDialog = AlertUtils.createProgressDialog(getActivity());
        alertDialog.show();
        btnLogout.setOnClickListener(this);
        apiCallProfile();

        ivEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void apiCallProfile() {
        APIServices services = APIClient.getClient(GeneralUtils.getApiToken(getActivity())).create(APIServices.class);
        Call<ProfileResponseModel> userLogin = services.profile();
        userLogin.enqueue(new Callback<ProfileResponseModel>() {
            @Override
            public void onResponse(Call<ProfileResponseModel> call, Response<ProfileResponseModel> response) {
                alertDialog.dismiss();
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout:
                GeneralUtils.putBooleanValueInEditor(getActivity(), "isLogin", false);
                startActivity(new Intent(getActivity(), SplashScreenActivity.class));
                getActivity().finish();
        }
    }
}
