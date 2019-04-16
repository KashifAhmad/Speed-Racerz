package com.techease.speedracerz.views;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.techease.speedracerz.R;
import com.techease.speedracerz.adapters.CityAdapter;
import com.techease.speedracerz.adapters.CountryAdapter;
import com.techease.speedracerz.dataModels.signupModels.SignupResponseModel;
import com.techease.speedracerz.dataModels.signupModels.cities.CitiesDataModel;
import com.techease.speedracerz.dataModels.signupModels.cities.CitiesResponseModel;
import com.techease.speedracerz.dataModels.signupModels.country.CountryDataModel;
import com.techease.speedracerz.dataModels.signupModels.country.CountryResponseModel;
import com.techease.speedracerz.interfaces.OnCityItemClicked;
import com.techease.speedracerz.interfaces.OnCountryItemClicked;
import com.techease.speedracerz.networking.BaseNetworking;
import com.techease.speedracerz.utils.Connectivity;
import com.techease.speedracerz.utils.GPSTracker;
import com.techease.speedracerz.utils.SharedPrefUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RacerRegistrationActivity extends AppCompatActivity implements View.OnClickListener, OnCountryItemClicked, OnCityItemClicked {

    @BindView(R.id.btn_racer_register)
    Button btnRacerRegister;


    @BindView(R.id.rl_user)
    RelativeLayout rlUser;
    @BindView(R.id.rl_racer)
    RelativeLayout rlRacer;


    @BindView(R.id.et_racer_username)
    EditText etUsername;
    @BindView(R.id.til_racer_username)
    TextInputLayout tilUsername;

    @BindView(R.id.et_racer__email_address)
    EditText etEmailAddress;
    @BindView(R.id.til_racer_email)
    TextInputLayout tilEmail;

    @BindView(R.id.et_racer_password)
    EditText etPassword;
    @BindView(R.id.til_racer_password)
    TextInputLayout tilPassword;


    @BindView(R.id.et_racer_country)
    EditText etCountry;
    @BindView(R.id.til_racer_country)
    TextInputLayout tilCountry;


    @BindView(R.id.et_racer_company)
    EditText etCompany;
    @BindView(R.id.til_racer_company)
    TextInputLayout tillCompany;

    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.til_address)
    TextInputLayout tilAddress;

    @BindView(R.id.spinner)
    Spinner countrySpinner;
    @BindView(R.id.category_spinner)
    Spinner categorySpinner;
    @BindView(R.id.city_spinner)
    Spinner citySpinner;

    @BindView(R.id.tv_already_member_login)
    TextView tvAlreadyMemberLogin;
    String[] testItems = {"First", "Second"};
    CountryAdapter countryAdapter;
    List<CountryDataModel> countryList;
    List<CitiesDataModel> cityList;
    CityAdapter cityAdapter;
    private String strUsername, strEmail, strPassword, userType = "", address, strCountry, strCity, strCompany, category, lat, lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_racer_registration);


        initUI();
    }

    private void initUI() {
        ButterKnife.bind(this);
        rlRacer.setOnClickListener(this);
        rlUser.setOnClickListener(this);
        cityList = new ArrayList<>();
        cityAdapter = new CityAdapter(this, cityList, this);
        citySpinner.setAdapter(cityAdapter);
        btnRacerRegister.setOnClickListener(this);
        tvAlreadyMemberLogin.setOnClickListener(this);
        initCountrySpinner();

        checkPermissions();
        getCoordinates();


    }

    private void getCoordinates() {
        SmartLocation.with(this).location()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        lat = String.valueOf(location.getLatitude());
                        lon = String.valueOf(location.getLongitude());

                    }
                });

        GPSTracker gpsTracker = new GPSTracker(this);
        lat = String.valueOf(gpsTracker.getLatitude());
        lon = String.valueOf(gpsTracker.getLongitude());

    }

    private void checkPermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();
    }


    private void initCountrySpinner() {

        countryList = new ArrayList<>();
        countryAdapter = new CountryAdapter(RacerRegistrationActivity.this, countryList, this);

        getCountries();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, testItems);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        categorySpinner.setSelection(0);
        category = categorySpinner.getSelectedItem().toString();
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countryAdapter.getItem(position);
                strCountry = countryList.get(position).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        countrySpinner.setOnItemSelectedListener(this);
    }

    private void getCountries() {
        Call<CountryResponseModel> call = BaseNetworking.apiServices().getCountries();
        call.enqueue(new Callback<CountryResponseModel>() {
            @Override
            public void onResponse(Call<CountryResponseModel> call, Response<CountryResponseModel> response) {
                if (response.isSuccessful()) {
                    countryList.addAll(response.body().getData());
                    countrySpinner.setAdapter(countryAdapter);
                    countryAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<CountryResponseModel> call, Throwable t) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_racer_register:
                if (Connectivity.isConnected(this)) {
                    if (validate()) {
                        makeUserRegistered();
                    }
                } else {
                    Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.tv_already_member_login:

                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.rl_user:
                userType = "user";
                categorySpinner.setVisibility(View.GONE);
                rlRacer.setBackgroundColor(getResources().getColor(R.color.round_gry_color));
                rlUser.setBackgroundColor(getResources().getColor(R.color.brown_text_color));

                break;
            case R.id.rl_racer:
                userType = "racer";
                categorySpinner.setVisibility(View.VISIBLE);
                rlUser.setBackgroundColor(getResources().getColor(R.color.round_gry_color));
                rlRacer.setBackgroundColor(getResources().getColor(R.color.brown_text_color));

                break;

        }
    }

    private void makeUserRegistered() {
        Call<SignupResponseModel> userRegistrationCall = BaseNetworking.apiServices().userSignUp(strUsername, strEmail, strPassword, userType, category, strCountry, strCity, address, strCompany, lat, lon, "Android", SharedPrefUtils.getDeviceToken(RacerRegistrationActivity.this));
        userRegistrationCall.enqueue(new Callback<SignupResponseModel>() {
            @Override
            public void onResponse(Call<SignupResponseModel> call, Response<SignupResponseModel> response) {
                if (response.isSuccessful()) {
                    startActivity(new Intent(RacerRegistrationActivity.this, LocationAccessActivity.class));

                } else {
                    Toast.makeText(RacerRegistrationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignupResponseModel> call, Throwable t) {

            }
        });
    }

    private boolean validate() {
        boolean valid = true;

        strUsername = etUsername.getText().toString();
        strEmail = etEmailAddress.getText().toString();
        strPassword = etPassword.getText().toString();
//        strCountry = countrySpinner.getSelectedItem().toString();
//        strCity = citySpinner.getSelectedItem().toString();
        strCompany = etCompany.getText().toString();
        strCompany = etCompany.getText().toString();
        address = etAddress.getText().toString();


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
        if (userType.equals("")) {
            Toast.makeText(this, "Please select a user type", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if (address.isEmpty() || address.length() < 3) {
            tilAddress.setError("enter valid address");
            valid = false;
        } else {
            tilAddress.setError(null);
        }
        if (strCompany.isEmpty() || strCompany.length() < 3) {
            tillCompany.setError("enter country");
            valid = false;
        } else {
            tillCompany.setError(null);
        }

        return valid;

    }

    @Override
    public void onCountryClick(int id) {
        getCities(id);
    }

    @Override
    public void onCountrySelected(String name) {
        strCountry = name;
        Log.d("zma country name", name);
    }

    private void getCities(int id) {
        Call<CitiesResponseModel> call = BaseNetworking.apiServices().getCities(id);
        call.enqueue(new Callback<CitiesResponseModel>() {
            @Override
            public void onResponse(Call<CitiesResponseModel> call, Response<CitiesResponseModel> response) {
                if (response.isSuccessful()) {
                    cityList.addAll(response.body().getData());
                    cityAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CitiesResponseModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCitySelected(String name) {
        strCity = name;
        Log.d("zma city", name);
    }
}
