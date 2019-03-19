package com.techease.speedracerz.views;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.techease.speed.R;
import com.techease.speedracerz.dataModels.loginModels.LoginResponse;
import com.techease.speedracerz.networking.APIClient;
import com.techease.speedracerz.networking.APIServices;
import com.techease.speedracerz.utils.AlertUtils;
import com.techease.speedracerz.utils.GeneralUtils;
import com.techease.speedracerz.views.resetpasswordscreens.ResetPasswordActivity;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
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


    @BindView(R.id.iv_login_facebook)
    ImageView ivLoginFacebook;
    @BindView(R.id.iv_login_google)
    ImageView ivLoginGoogle;
    @BindView(R.id.iv_login_twitter)
    ImageView ivLoginTwitter;
    @BindView(R.id.iv_login_instagram)
    ImageView ivLoginInstagrarm;

    AlertDialog alertDialog;
    String strLoginUsername, strLoginPassword,strToken;


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

    private void apiCallLogin() {
        APIServices services = APIClient.getClient().create(APIServices.class);
        Call<LoginResponse> userLogin = services.userLogin(strLoginUsername, strLoginPassword, "5434.45", "5434.478", "3d5d943946b39461fffd2a99e2b0d7734ec137cecae2f1d3153c3c2d9bf2231a");
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
                    GeneralUtils.putBooleanValueInEditor(LoginActivity.this, "isLogin", true);
                    GeneralUtils.putStringValueInEditor(LoginActivity.this, "api_token", strToken);
                    startActivity(new Intent(LoginActivity.this, EventActivity.class));
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
