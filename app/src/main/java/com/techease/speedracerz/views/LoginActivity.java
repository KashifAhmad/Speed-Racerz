package com.techease.speedracerz.views;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.techease.speedracerz.R;
import com.techease.speedracerz.dataModels.loginModels.LoginResponse;
import com.techease.speedracerz.networking.APIClient;
import com.techease.speedracerz.networking.APIServices;
import com.techease.speedracerz.utils.AlertUtils;
import com.techease.speedracerz.utils.GPSTracker;
import com.techease.speedracerz.utils.GeneralUtils;
import com.techease.speedracerz.utils.SharedPrefUtils;
import com.techease.speedracerz.views.resetpasswordscreens.ResetPasswordActivity;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    @BindView(R.id.rb_remember_me)
    RadioButton rbRememberMe;


    @BindView(R.id.iv_login_facebook)
    ImageView ivLoginFacebook;
    @BindView(R.id.iv_login_google)
    ImageView ivLoginGoogle;
    @BindView(R.id.iv_login_twitter)
    ImageView ivLoginTwitter;
    @BindView(R.id.iv_login_instagram)
    ImageView ivLoginInstagrarm;

    AlertDialog alertDialog;
    String strLoginUsername, strLoginPassword, strToken, lat, lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setContentView(R.layout.activity_login);

        initUI();
    }

    private void initUI() {
        ButterKnife.bind(this);
        buttonClickListener();
        checkPermissions();
        getCoordinates();

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
                    alertDialog = AlertUtils.createProgressDialog(this);
                    alertDialog.show();
                    apiCallLogin();
                }
                break;
            case R.id.tv_forget_password:
                startActivity(new Intent(this, ResetPasswordActivity.class));
                break;
            case R.id.tv_new_here_register:

                finish();
                startActivity(new Intent(this, RacerRegistrationActivity.class));
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

    private void apiCallLogin() {
        APIServices services = APIClient.getClient().create(APIServices.class);
        Call<LoginResponse> userLogin = services.userLogin(strLoginUsername, strLoginPassword, lat, lon, SharedPrefUtils.getDeviceToken(this));
        userLogin.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                alertDialog.dismiss();
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(LoginActivity.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else if (response.body().getStatus()) {
                    strToken = response.body().getData().getUser().getToken();
                    if (rbRememberMe.isChecked()) {
                        GeneralUtils.putBooleanValueInEditor(LoginActivity.this, "isLogin", true);
                    }
                    GeneralUtils.putStringValueInEditor(LoginActivity.this, "api_token", strToken);
                    SharedPrefUtils.getEditor(LoginActivity.this).putString("name", response.body().getData().getUser().getName()).commit();
                    SharedPrefUtils.getEditor(LoginActivity.this).putString("auth_token", strToken).commit();
                    SharedPrefUtils.getEditor(LoginActivity.this).putString("email", strLoginUsername).commit();
                    SharedPrefUtils.getEditor(LoginActivity.this).putString("address", response.body().getData().getUser().getAddress()).commit();
                    Intent intent = new Intent(LoginActivity.this, BottomNavActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finishAffinity();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
